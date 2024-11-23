package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.frontend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.dtos.CandidateDto;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.dtos.JobDto;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CandidateService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CompanyService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.JobService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.SkillService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateService candidateService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public String getCompanyAndJobsByCompanyId(@RequestParam("id") Long id, Model model) {
//      Hiển thị thông tin công ty
        model.addAttribute("company", companyService.findById(id));

//      lấy các kỹ năng của các công việc trong công ty
        Map<Job, List<Skill>> jobsWithSkills = jobService.getJobsWithSkillsByCompanyId(id);
        model.addAttribute("jobsWithSkills", jobsWithSkills);
        return "company";
    }

    @GetMapping("/candidates")
    public String getCandidate(Model model, @RequestParam("jobId") Long jobId,
                               @RequestParam(value = "canId", required = false) Long canId) {

//      Hiển thị thông tin ứng viên
        if (canId != null) {
            Candidate candidate = candidateService.findById(canId);
            CandidateDto candidateDto = modelMapper.map(candidate, CandidateDto.class);
            candidateDto.setSkills(skillService.getSkillsByCandidateId(canId));
            List<String> skillString = new ArrayList<>();
            candidateDto.getSkills().forEach(skill -> {
                skillString.add(skill.getSkillName());
            });
            candidateDto.setSkillString(String.join(", ", skillString));
            model.addAttribute("candidate", candidateDto);
        }

//      Hiển thị thông tin công ty
        Company company = jobService.findById(jobId).getCompany();
        model.addAttribute("company", company);

//      Hiển thị thông tin công việc
        Map<Job, List<Skill>> jobsWithSkills = jobService.getJobsWithSkills(jobId);
        model.addAttribute("jobWithSkills", jobsWithSkills);

//      Hiển thị thông tin ứng viên


//      lấy các candidate có kỹ năng phù hợp với công việc
        Map<Long, Integer> listCan = new LinkedHashMap<>();
        skillService.getJobSkillsByJobId(jobId).forEach(jobSkill -> {
            skillService.getCandidateSkillsBySkillId(jobSkill.getSkill().getId()).forEach(candidateSkill -> {
                if (listCan.containsKey(candidateSkill.getCan().getId())) {
                    listCan.put(candidateSkill.getCan().getId(), listCan.get(candidateSkill.getCan().getId()) + 1);
                } else {
                    listCan.put(candidateSkill.getCan().getId(), 1);
                }
            });
        });

        Map<Long, Integer> listCanSort = listCan.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        ArrayList<Long> listCanId = new ArrayList<>(listCanSort.keySet());

        List<Candidate> cans = listCanId.stream().map(candidateService::findById).toList();
        List<CandidateDto> canDto = cans.stream().map((element) -> modelMapper.map(element, CandidateDto.class)).toList();
        canDto.forEach(candidateDto -> {

            candidateDto.setSkills(skillService.getSkillsByCandidateId(candidateDto.getId()));
            List<String> skillString = new ArrayList<>();
            candidateDto.getSkills().forEach(skill -> {
                skillString.add(skill.getSkillName());
            });
            candidateDto.setSkillString(String.join(", ", skillString));
        });

        model.addAttribute("cans", canDto);

        return "candidates";
    }

}
