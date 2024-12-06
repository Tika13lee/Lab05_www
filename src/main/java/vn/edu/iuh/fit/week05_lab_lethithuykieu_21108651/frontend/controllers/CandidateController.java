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
    public String getCandidateJobs(
            @RequestParam Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model
    ) {

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
                listJob.merge(jobSkill.getJob().getId(), 1, Integer::sum);
            });
        });

        // Sắp xếp và chuyển sang danh sách ID
        Map<Long, Integer> listJobSort = listJob.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        List<Long> listJobId = new ArrayList<>(listJobSort.keySet());

        // Phân trang thủ công
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, listJobId.size());
        List<Long> paginatedJobIds = listJobId.subList(startIndex, endIndex);

        // Tạo danh sách JobDtos từ các ID phân trang
        List<Job> jobs = paginatedJobIds.stream().map(jobService::findById).toList();
        List<JobDto> jobDtos = jobs.stream().map(job -> {
            JobDto jobDto = modelMapper.map(job, JobDto.class);
            jobDto.setSkills(skillService.getSkillsByJobId(job.getId()));
            List<String> skillString = jobDto.getSkills().stream()
                    .map(skill -> skill.getSkillName() + " (" +
                            skillService.getJobSkillById(new JobSkillId(job.getId(), skill.getId())).getSkillLevel() + ")")
                    .toList();
            jobDto.setSkillString(String.join(", ", skillString));
            return jobDto;
        }).toList();

        // Tính tổng số trang
        int totalPages = (int) Math.ceil((double) listJobId.size() / size);

        model.addAttribute("id", id);
        model.addAttribute("size", size);
        model.addAttribute("jobs", jobDtos);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "candidate";
    }

}
