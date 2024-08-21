/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bookstore.service;

import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author infoh
 */
@Service
@AllArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    
    @Autowired
    UserRepository ur;

    public void sendRegisterToken(User user) {
        user.refreshToken();
        ur.save(user);
        
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        var message = new StringBuilder();
        message.append("Click the link to verify your email: ");
        message.append("\r\n");
        message.append("http://localhost:8080/auth/process-token?token=");
        message.append(user.getToken());
        
        send(recipientAddress, subject, message.toString());
    }
    
    public void send(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
