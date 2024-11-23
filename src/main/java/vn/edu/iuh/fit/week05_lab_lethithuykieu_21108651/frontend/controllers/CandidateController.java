package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.frontend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.dtos.JobDto;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.*;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.JobService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.SkillService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl.CandidateImpl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    private CandidateImpl candidateService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private JobService jobService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public String getJobs(Model model, @RequestParam("id") Long id) {
//        Hiển thị thông tin candidate
        Candidate candidate = candidateService.findById(id);
        model.addAttribute("candidate", candidate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        model.addAttribute("dob", candidate.getDob().format(formatter));

        Map<String, List<CandidateSkill>> candidateSkillsByType = skillService.getCandidateSkillsByCandidateId(id).stream()
                .collect(Collectors.groupingBy(candidateSkill -> candidateSkill.getSkill().getType().name()));

        model.addAttribute("skillsByType", candidateSkillsByType);

//        Hiển thị danh sách job phù hợp với các skill của candidate
        Map<Long, Integer> listJob = new LinkedHashMap<>();

        skillService.getCandidateSkillsByCandidateId(id).forEach(candidateSkill -> {
            skillService.getJobSkillsBySkillId(candidateSkill.getSkill().getId()).forEach(jobSkill -> {
                if (listJob.containsKey(jobSkill.getJob().getId())) {
                    listJob.put(jobSkill.getJob().getId(), listJob.get(jobSkill.getJob().getId()) + 1);
                } else {
                    listJob.put(jobSkill.getJob().getId(), 1);
                }
            });
        });
        Map<Long,Integer> listJobSort = listJob.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        ArrayList<Long> listJobId = new ArrayList<>(listJobSort.keySet());

        List<Job> jobs = listJobId.stream().map(jobService::findById).toList();
        List<JobDto> jobDtos= jobs.stream().map((element) -> modelMapper.map(element, JobDto.class)).toList();
        jobDtos.forEach(jobDto -> {

            jobDto.setSkills(skillService.getSkillsByJobId(jobDto.getId()));
            List<String> skillString = new ArrayList<>();
            jobDto.getSkills().forEach(skill ->{
                skillString.add(skill.getSkillName()+" ("+skillService.getJobSkillById(new JobSkillId(jobDto.getId(),skill.getId())).getSkillLevel()+")");
            });
            jobDto.setSkillString(String.join(", ",skillString));
        });

        model.addAttribute("jobs", jobDtos);

        return "candidate";
    }


}
