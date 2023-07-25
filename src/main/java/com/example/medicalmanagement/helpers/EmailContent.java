package com.example.medicalmanagement.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailContent {
    private EmailContent() {
    }

    public static String generateAppointmentCancellationEmail(String userName, String doctorName, LocalDateTime appointmentDate) {
        String formattedAppointmentDate = appointmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return  "Hello " + userName + ",\n\n"
                +  "Your had an appointment " +  "on " + formattedAppointmentDate + " with Doctor " + doctorName + "\n Your appointment has been cancelled due to a possible Ferrari win!"+ "\n"+"Regards\n The best online medical center";


    }
}


