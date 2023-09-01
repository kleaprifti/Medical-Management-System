package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.model.NotificationType;
import com.example.medicalmanagement.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String idMedicalCard;
    private ContactInfo contactInfo;
    private List<UserRole> roles;
    private List<String> specialities;
    private boolean emailSent;
    private List<NotificationType> notificationTypes;

}