package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Job;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.JobRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.JobService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public void save(Job job) {
        jobRepository.save(job);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        jobSkillRepository.deleteJobSkillsById_JobId(id);
        jobRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Job job) {
        if (job.getId() != null && jobRepository.existsById(job.getId())) {
            jobRepository.save(job);
        } else {
            throw new IllegalArgumentException("Job ID must exist for update");
        }
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Map<Job, List<Skill>> getJobsWithSkillsByCompanyId(Long id) {
        List<Job> jobs = jobRepository.findJobsByCompanyId(id);
        Map<Job, List<Skill>> jobSkillsMap = new LinkedHashMap<>();

        jobs.forEach(job -> {
            List<Skill> skills = jobSkillRepository.findJobSkillsById_JobId(job.getId()).stream()
                    .map(js -> skillRepository.findById(js.getId().getSkillId()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            jobSkillsMap.put(job, skills);
        });

        return jobSkillsMap;
    }

    @Override
    public void addJobSkill(JobSkill jobSkill) {
        jobSkillRepository.save(jobSkill);
    }

    @Override
    public Map<Job, List<Skill>> getJobsWithSkills(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        Map<Job, List<Skill>> jobSkillsMap = new LinkedHashMap<>();

        if (job != null) {
            List<JobSkill> jobSkills = jobSkillRepository.findJobSkillsById_JobId(job.getId());
            List<Skill> skills = jobSkills.stream()
                    .map(js -> skillRepository.findById(js.getId().
                            getSkillId()).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            jobSkillsMap.put(job, skills);
        }
        return jobSkillsMap;
    }
}
