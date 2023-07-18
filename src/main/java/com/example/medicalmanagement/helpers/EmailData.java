package com.example.medicalmanagement.helpers;


import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailData {
    private User user;
    private Appointment appointment;
    private EmailContent emailContent;

}
