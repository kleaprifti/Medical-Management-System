package com.example.medicalmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private UserRole userRole;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Role(UserRole userRole) {
        this.userRole = userRole;
    }

    public Role(long l, UserRole userRole) {
    }
}

