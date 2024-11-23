package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CandidateService;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.CompanyService;

@Controller
public class LoginController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CandidateService candidateService;

    @GetMapping("/checkLogin")
    public String checkLogin(@RequestParam("id") Long id,
                             @RequestParam("role") String role,
                             Model model) {
        if (id == null || role == null || role.isEmpty()) {
            model.addAttribute("error", "Both ID and Role are required!");
            return "index";
        }

        if (role.equals("company")) {
            if (companyService.existsById(id)) {
                return "redirect:/company?id=" + id;
            } else {
                model.addAttribute("error", "Company ID does not exist!");
                return "index";
            }
        } else if (role.equals("candidate")) {
            if (candidateService.existsById(id)) {
                return "redirect:/candidate?id=" + id;
            } else {
                model.addAttribute("error", "Candidate ID does not exist!");
                return "index";
            }
        } else {
            model.addAttribute("error", "Invalid role selected!");
            return "index";
        }
    }
}
