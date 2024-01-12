package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotBlank(message = "Email cant be blank")
    @Email(message = "Bad Email Format")
    @Column
    private String email;
    @Column
    private String password;
    @OneToOne(cascade =CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "user_details_id", referencedColumnName = "id")
    private UserDetails userDetails;

    public String getAuthToken() {
        return UUID.randomUUID().toString();
    }

    public Long getUsername() {
        return getUsername();
    }
}
