package com.example.medicalmanagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Transactional
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "id_medical_card", nullable = false,unique = true)
    private String idMedicalCard;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_speciality",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "speciality_id")})
    private List<Speciality> specialities;
    @ManyToMany(fetch = FetchType.EAGER)
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

    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable    (name = "user_availability",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "doctor_availability_id") )
    private List<DoctorAvailability> doctorAvailabilities;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_holidays",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id")
    )
    private List<Holidays> holidays;

    public User(String fullName, String birthDate, String idMedicalCard, String roles, String specialities) {
    }

    public User(String fullName, String birthDate, String idMedicalCard, String specialities) {
    }

    public User(String fullName, String birthDate, String idMedicalCard) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return idMedicalCard != null ? idMedicalCard.equals(user.idMedicalCard) : user.idMedicalCard == null;
    }

    @Override
    public int hashCode() {
        return idMedicalCard != null ? idMedicalCard.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", idMedicalCard='" + idMedicalCard + '\'' +
                ", roles=" + roles +
                ", specialities=" + specialities +
                ", notificationTypes=" + notificationTypes +
                ", contactInfo=" + contactInfo +
                ", doctorAvailabilities=" + doctorAvailabilities +
                ", holidays=" + holidays +
                '}';
    }
}

