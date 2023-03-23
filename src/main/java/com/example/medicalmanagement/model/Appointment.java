package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.time.LocalDateTime;


@Entity
@Table (name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "patient_id")
        @JsonIgnore
        private Patient patient;

       private LocalDateTime appointmentDateTime;

        @ManyToOne
        @JoinColumn(name = "doctor_id")
        @JsonIgnore
        private Doctor doctor;

    }


