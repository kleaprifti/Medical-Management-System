package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.customvalidators.SpecialitiesRequiredForDoctor;
import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.model.NotificationType;
import com.example.medicalmanagement.model.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
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
@SpecialitiesRequiredForDoctor
public class UserDto {

    private Long id;
    @NotNull(message = "Please provide user full name")
    private String fullName;
    @NotNull
    @Past
    private LocalDate birthDate;
    @NotNull
    @Pattern(regexp = "^.{16}$", message = "Medical ID should consist of 16 characters")
    private String idMedicalCard;
    @NotNull
    @Valid
    private ContactInfo contactInfo;
    @NotNull(message = "Each user must have a role associated")
    private List<UserRole> roles;
    private List<String> specialities;
    private boolean emailSent;
    private List<NotificationType> notificationTypes;

}