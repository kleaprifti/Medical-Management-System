package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(name = "appointmentDateStartTime")
    private LocalDateTime appointmentDateStartTime;

    @Column(name = "appointmentDateEndTime")
    private LocalDateTime appointmentDateEndTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private UserDetails doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private UserDetails patient;


    @Override
    public String toString() {
        return "\n•" + appointmentDateStartTime.format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy ")) + ", " + patient.getFullName();
    }

}



