package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


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
    @JoinColumn(name = "patient_id", referencedColumnName = "Id")
    @JsonIgnore
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @JsonIgnore
    private Doctor doctor;

}


