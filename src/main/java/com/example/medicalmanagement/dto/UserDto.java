package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.model.NotificationType;
import com.example.medicalmanagement.model.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String email;

    private String fullName;

    @JsonIgnore
    private List<UserRole> roles;

    @JsonIgnore
    private List<String> specialities;

    @JsonIgnore
    private boolean emailSent;

    @JsonIgnore
    private List<NotificationType> notificationTypes;


    public UserDto(Long id, String email, String fullName, List<UserRole> roles, List<String> specialities, List<NotificationType> notificationTypes) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
        this.specialities = specialities;
        this.notificationTypes = notificationTypes;
    }

}