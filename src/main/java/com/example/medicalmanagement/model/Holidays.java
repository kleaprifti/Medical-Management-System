package com.example.medicalmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctor_holidays")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Holidays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "holiday_date")
    private LocalDate holidayDate;

    @Column(name = "holiday_name",unique = true)
    private String name;

    @ManyToMany(mappedBy = "holidays")
    private List<UserDetails> users;

}
