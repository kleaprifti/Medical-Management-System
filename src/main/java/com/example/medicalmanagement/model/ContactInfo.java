package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contact_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContactInfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "phone_number",nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "slack_Username", unique = true)
    private String slackUserName ;

    @OneToOne(mappedBy = "contactInfo")
    @JsonBackReference
    private User user;
}


