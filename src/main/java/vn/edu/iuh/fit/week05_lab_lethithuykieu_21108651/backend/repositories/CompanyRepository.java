package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsById(@NonNull Long id);

}