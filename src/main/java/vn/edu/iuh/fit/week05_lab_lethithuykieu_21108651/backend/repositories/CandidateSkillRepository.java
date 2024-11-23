package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.CandidateSkillId;

import java.util.List;

@Repository
public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    List<CandidateSkill> findCandidateSkillsById_CanId(Long id);
    List<CandidateSkill> findCandidateSkillsById_SkillId(Long id);
}