package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.JobSkillId;

import java.util.List;

@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
    List<JobSkill> findJobSkillsById_JobId(Long id);
    void deleteJobSkillsById_JobId(Long id);
    List<JobSkill> findJobSkillsById_SkillId(Long id);
}