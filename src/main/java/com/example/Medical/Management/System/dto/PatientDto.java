package com.example.Medical.Management.System.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;

}
