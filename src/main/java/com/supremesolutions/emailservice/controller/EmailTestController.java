package com.supremesolutions.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class EmailTestController {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailTestController(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Simple test: /send-test-email?to=someone@example.com&subject=Hi&body=Hello
     */
    @GetMapping("/send-test-email")
    public String sendTestEmail(
            @RequestParam String to,
            @RequestParam(defaultValue = "Test from Email Service") String subject,
            @RequestParam(defaultValue = "Hello from email-service") String body) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("info@supremebuildsolutions.com");
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            // From will default to spring.mail.username if not set

            mailSender.send(msg);
            return "✅ Email sent to " + to;
        } catch (Exception e) {
            return "❌ Email failed: " + e.getMessage();
        }
    }
}
