package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.model.NotificationType;
import com.example.medicalmanagement.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @JsonIgnore
    private List<UserRole> roles;

    @JsonIgnore
    private List<String> specialities;

    @JsonIgnore
    private boolean emailSent;

    @JsonIgnore
    private List<NotificationType> notificationTypes;



    public UserDto(Long id, String fullName,LocalDate birthDate,String idMedicalCard, ContactInfo contactInfo,List<UserRole> roles, List<String> specialities, List<NotificationType> notificationTypes) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.idMedicalCard = idMedicalCard;
        this.contactInfo = contactInfo;
        this.roles = roles;
        this.specialities = specialities;
        this.notificationTypes = notificationTypes;
    }


}