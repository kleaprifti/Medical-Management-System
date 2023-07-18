package com.example.medicalmanagement.service;



import com.example.medicalmanagement.helpers.EmailContent;
import com.example.medicalmanagement.helpers.EmailData;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
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
    public void sendAppointmentCancellationEmail(EmailData emailData) {
        User user = emailData.getUser();
        Appointment appointment = emailData.getAppointment();

        String userEmail = user.getEmail();
        String cancellationEmailContent = EmailContent.generateAppointmentCancellationEmail(user.getFullName(), appointment.getAppointmentDateStartTime());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Cancellation of appointment");
        message.setText(cancellationEmailContent);

        javaMailSender.send(message);
    }

}

