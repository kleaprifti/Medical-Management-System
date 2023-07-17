package com.example.medicalmanagement.helpers;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailData {
    private UserDto userDto;
    private AppointmentDto appointmentDto;
    private EmailContent emailContent;

    public Long getAppointmentId(AppointmentDto appointmentDto){
        return appointmentDto.getAppointmentId();
    }


// get email , get appointment id , get email content
}
