package com.utkarsh.ecombackend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendOtp(String to, String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("One Time Password");
        message.setText("Your otp is "+ otp
        + """
                
                this otp will expire after 5 minutes
                """);

        javaMailSender.send(message);
    }
}
