package com.example.medicalmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "full_name",nullable = false)
    private String fullName;

    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @Column(name = "phone_number",nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "id_medicalCard", nullable = false,unique = true)
    private String idMedicalCard;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    @ManyToMany
    @JoinTable(name = "user_speciality",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "speciality_id")})
    private List<Speciality> specialities;
    @ManyToMany
    @JoinTable(
            name = "user_notification_mapping",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<UserNotificationType> notificationTypes;
}