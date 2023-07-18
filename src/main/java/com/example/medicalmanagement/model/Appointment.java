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
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "patient_name")
    private User patientFullName;


    @Override
    public String toString() {
        return "\n• Appointment :" +
                "\nStart Date Time='" + appointmentDateStartTime + "\nEnd Date Time="+ appointmentDateEndTime+ '\'' +
                ",\n Name of the patient :' " + patient.getFullName() + "!'";
    }

}



