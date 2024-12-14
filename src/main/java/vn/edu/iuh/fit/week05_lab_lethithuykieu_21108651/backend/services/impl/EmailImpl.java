package vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05_lab_lethithuykieu_21108651.backend.services.EmailService;

import java.util.Properties;

@Service
public class EmailImpl implements EmailService {
    @Override
    public JavaMailSender createMailSender(String username, String password) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body, String fromEmail, String fromPassword) {
        JavaMailSender mailSender = createMailSender(fromEmail, fromPassword);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(fromEmail);

        mailSender.send(message);
    }
}
