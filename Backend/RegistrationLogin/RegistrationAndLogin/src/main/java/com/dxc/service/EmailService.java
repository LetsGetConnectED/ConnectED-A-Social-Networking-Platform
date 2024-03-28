package com.dxc.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
@Service


public class EmailService {
	private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendResetPasswordEmail(String to, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Reset Your Password");
        message.setText("To reset your password, click the following link: http://example.com/reset-password?token=" + token);
        javaMailSender.send(message);
    }
}

	
	

