package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

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

    @OneToOne(cascade =CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "contact_info_id", referencedColumnName = "id")
    private ContactInfo contactInfo;

    @ManyToOne
    @JoinColumn(name = "authority_id",referencedColumnName = "id")
    private Authority authority;

    @ManyToMany
    @JoinTable    (name = "user_availability",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_availability_id") )
    private List<DoctorAvailability> doctorAvailabilities;

    @ManyToMany
    @JoinTable(
            name = "user_holidays",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id")
    )
    private List<Holidays> holidays;
    @OneToOne(mappedBy = "userDetails")
    @JsonBackReference
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails userDetails = (UserDetails) o;

        return idMedicalCard != null ? idMedicalCard.equals(userDetails.idMedicalCard) : userDetails.idMedicalCard == null;
    }

    @Override
    public int hashCode() {
        return idMedicalCard != null ? idMedicalCard.hashCode() : 0;
    }
}