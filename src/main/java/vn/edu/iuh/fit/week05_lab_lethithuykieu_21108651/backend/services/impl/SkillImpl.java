package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkillId;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.JobSkillRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.SkillRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.SkillService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillImpl implements SkillService {
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;


    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    @Override
    public List<Skill> getSkillsByCandidateId(Long id) {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findCandidateSkillsById_CanId(id);
        List<Skill> skills = new ArrayList<>();
        candidateSkills.forEach(candidateSkill -> {
            skills.add(skillRepository.findById(candidateSkill.getId().getSkillId()).orElse(null));
        });
        return skills;
    }

    @Override
    public List<Skill> getSkillsByJobId(Long id) {
        List<JobSkill> jobSkills = jobSkillRepository.findJobSkillsById_JobId(id);
        List<Skill> skills = new ArrayList<>();
        jobSkills.forEach(jobSkill -> {
            skills.add(skillRepository.findById(jobSkill.getId().getSkillId()).orElse(null));
        });
        return skills;
    }

    @Override
    public List<JobSkill> getJobSkillsByJobId(Long id) {
        return jobSkillRepository.findJobSkillsById_JobId(id);
    }

    @Override
    public List<CandidateSkill> getCandidateSkillsByCandidateId(Long id) {
        return candidateSkillRepository.findCandidateSkillsById_CanId(id);
    }

    @Override
    public List<JobSkill> getJobSkillsBySkillId(Long id) {
        return jobSkillRepository.findJobSkillsById_SkillId(id);
    }

    @Override
    public JobSkill getJobSkillById(JobSkillId id) {
        return jobSkillRepository.findById(id).orElse(null);
    }

    @Override
    public List<CandidateSkill> getCandidateSkillsBySkillId(Long id) {
        return candidateSkillRepository.findCandidateSkillsById_SkillId(id);
    }

}
