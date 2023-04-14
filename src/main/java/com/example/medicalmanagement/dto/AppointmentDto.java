package com.example.medicalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {
    private Long  appointmentId;
    private Long doctorId;
    private Long patientId;
    private LocalDateTime appointmentDateStartTime;
    private LocalDateTime appointmentDateEndTime;


}