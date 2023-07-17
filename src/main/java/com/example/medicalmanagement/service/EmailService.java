package com.example.medicalmanagement.service;


import com.example.medicalmanagement.helpers.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendAppointmentCancellationEmail(EmailData emailData){

        SimpleMailMessage message= new SimpleMailMessage();
        message.setTo(patientEmail);
        message.setSubject("Cancellation of appointment");
        message.setText("Your appointment has been cancelled");

        javaMailSender.send(message);
    }
}
