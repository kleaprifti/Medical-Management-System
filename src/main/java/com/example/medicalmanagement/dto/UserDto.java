package com.example.medicalmanagement.dto;

import com.example.medicalmanagement.customvalidators.SpecialitiesRequiredForDoctor;
import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.model.DoctorAvailability;
import com.example.medicalmanagement.model.NotificationType;
import com.example.medicalmanagement.model.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Please provide user full name")
    private String fullName;
    @NotNull(message = "How can you not have a birthdate come on")
    @Past
    private LocalDate birthDate;
    @NotNull(message = "Medical Id can not be null")
    @Pattern(regexp = "^.{16}$", message = "Medical ID should consist of 16 characters")
    private String idMedicalCard;
    @NotNull (message = "Contact Info can not be null")
    @Valid
    private ContactInfo contactInfo;
    @NotNull(message = "Each user must have a role associated")
    @NotEmpty(message = "User Role can not be empty")
    private List<UserRole> roles;
    private List<String> specialities;
    private boolean emailSent;
    private List<NotificationType> notificationTypes;

}