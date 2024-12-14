package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    public JavaMailSender createMailSender(String username, String password);
    public void sendEmail(String to, String subject, String body, String fromEmail, String fromPassword);
}
