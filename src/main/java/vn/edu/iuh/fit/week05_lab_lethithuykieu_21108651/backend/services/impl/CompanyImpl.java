package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Company;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.CompanyRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CompanyService;

@Service
public class CompanyImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

}
