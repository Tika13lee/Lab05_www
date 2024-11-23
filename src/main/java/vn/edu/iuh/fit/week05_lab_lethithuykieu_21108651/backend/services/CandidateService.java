package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services;

import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.models.Candidate;

import java.util.List;

public interface CandidateService {
    public List<Candidate> getAllCandidates();
    public boolean existsById(Long id);
    public Candidate findById(Long id);
}
