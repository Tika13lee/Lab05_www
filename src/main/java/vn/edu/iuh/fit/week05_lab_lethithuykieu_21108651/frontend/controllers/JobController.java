package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.frontend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CompanyService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.JobService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.SkillService;

import java.util.*;

@Controller
@RequestMapping("/company")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private SkillService skillService;

    // hiển thị form thêm công việc
    @GetMapping("/add-job")
    public String showAddJobForm(@RequestParam("companyId") Long companyId, Model model) {
        Company company = companyService.findById(companyId);
        model.addAttribute("company", company);

        List<Skill> skills = skillService.getAllSkills();
        model.addAttribute("skills", skills);

        Job job = new Job();
        job.setCompany(company);
        model.addAttribute("job", job);
        model.addAttribute("show", true);
        return "add-job";
    }

    // lưu công việc
    @PostMapping("/save-job")
    public String saveJob(@ModelAttribute Job job,
                          @RequestParam("selectedSkillsData") String selectedSkillsData) throws Exception {

        if (job.getId() != null) {
            jobService.update(job);
        } else {
            jobService.save(job);
        }

        if (selectedSkillsData != null && !selectedSkillsData.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> selectedSkills = objectMapper.readValue(selectedSkillsData, List.class);

            for (Map<String, Object> skillData : selectedSkills) {
                Long skillId = Long.valueOf(skillData.get("skillId").toString());
                String skillLevel = skillData.get("skillLevel").toString();
                String moreInfos = skillData.get("moreInfos").toString();

                Skill skill = skillService.findById(skillId);

                JobSkillId jobSkillId = new JobSkillId();
                jobSkillId.setJobId(job.getId());
                jobSkillId.setSkillId(skill.getId());

                JobSkill jobSkill = new JobSkill();
                jobSkill.setId(jobSkillId);
                jobSkill.setJob(job);
                jobSkill.setSkill(skill);
                jobSkill.setSkillLevel(SkillLevel.valueOf(skillLevel));
                jobSkill.setMoreInfos(moreInfos);

                jobService.addJobSkill(jobSkill);
            }
        }
        return "redirect:/company?id=" + job.getCompany().getId();
    }

    // hiển thị form sửa công việc
    @GetMapping("/show-job")
    public String showEditJobForm(@RequestParam Long jobId, Model model) {
        Job job = jobService.findById(jobId);

        model.addAttribute("job", job);
        model.addAttribute("company", job.getCompany());
        model.addAttribute("skills", skillService.getAllSkills());

        List<JobSkill> selectedSkills = skillService.getJobSkillsByJobId(jobId);

        System.out.println("skill: " + selectedSkills);
        model.addAttribute("selectedSkills", selectedSkills);
        model.addAttribute("show", false);

        return "add-job";
    }


    // xóa công việc
    @PostMapping("/delete-job")
    public String deleteJob(@RequestParam Long jobId) {
        Job job = jobService.findById(jobId);
        Long companyId = job.getCompany().getId();
        jobService.delete(jobId);
        return "redirect:/company?id=" + companyId;
    }
}
