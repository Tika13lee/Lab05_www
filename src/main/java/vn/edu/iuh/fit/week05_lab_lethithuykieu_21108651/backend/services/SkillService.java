package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services;

import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkillId;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Skill;

import java.util.List;

public interface SkillService {
    public List<Skill> getAllSkills();
    public Skill findById(Long id);
    public List<Skill> getSkillsByCandidateId(Long id);
    public List<Skill> getSkillsByJobId(Long id);
    public List<JobSkill> getJobSkillsByJobId(Long id);
    public List<CandidateSkill> getCandidateSkillsByCandidateId(Long id);
    public List<JobSkill> getJobSkillsBySkillId(Long id);
    public JobSkill getJobSkillById(JobSkillId id);
    public List<CandidateSkill> getCandidateSkillsBySkillId(Long id);
}
