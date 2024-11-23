package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsById(Long id);
}