package com.example.medicalmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "doctor_availability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time",unique = true)
    private LocalDateTime endTime;
    @ElementCollection
    @CollectionTable(name = "doctor_working_days", joinColumns = @JoinColumn(name = "doctor_availability_id"))
    @Column(name = "working_day")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> workingDays;

    @ManyToMany(mappedBy = "doctorAvailabilities")
    private List<User> users;

}
