package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Job;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findJobsByCompanyId(Long id);
//    void updateJobById(Long id);
}