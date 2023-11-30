package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Phone Number cant be blank")
    @Pattern(regexp = "^\\d+$", message = "Bad phone number format, should contain only digits")
    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    @Column(name = "slack_Username", unique = true)
    private String slackUserName;
    @OneToOne(mappedBy = "contactInfo")
    @JsonBackReference
    private UserDetails user;


}


