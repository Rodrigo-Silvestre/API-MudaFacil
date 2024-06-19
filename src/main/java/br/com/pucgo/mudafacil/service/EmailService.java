package br.com.pucgo.mudafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMainSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(String recipient, String subject, String message) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(recipient);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            javaMainSender.send(simpleMailMessage);
            return "Email sent.";
        } catch (Exception e) {
            return "Error when trying to send the email.";
        }
    }
}
