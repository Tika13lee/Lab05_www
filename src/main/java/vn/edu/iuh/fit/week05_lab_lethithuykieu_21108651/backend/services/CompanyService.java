package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services;

import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Company;

public interface CompanyService {
    boolean existsById(Long id);
    Company findById(Long id);
}
