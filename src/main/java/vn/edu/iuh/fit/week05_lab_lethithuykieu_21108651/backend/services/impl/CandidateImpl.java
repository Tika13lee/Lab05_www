package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Candidate;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CandidateService;

import java.util.List;

@Service
public class CandidateImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return candidateRepository.existsById(id);
    }

    @Override
    public Candidate findById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }
}
