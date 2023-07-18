package com.example.medicalmanagement.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailContent {

    public static String generateAppointmentCancellationEmail(String userName,  LocalDateTime appointmentDate) {
        String formattedAppointmentDate = appointmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return  "Hello " + userName + ",\n\n"
                + "Your appointment  " +  " on " + formattedAppointmentDate + " is cancelled due to a possible ferrari win"+ "\n\n"+"Regards\n The best online medical center";

    }
}


