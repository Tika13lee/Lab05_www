package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services;

import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Job;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;

import java.util.List;
import java.util.Map;

public interface JobService {
    void save(Job job);

    void delete(Long id);

    void update(Job job);

    Job findById(Long id);

    Map<Job, List<Skill>> getJobsWithSkillsByCompanyId(Long companyId);

    void addJobSkill(JobSkill jobSkill);

    Map<Job, List<Skill>> getJobsWithSkills(Long id);
}
