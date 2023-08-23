package com.example.medicalmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user_notification_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserNotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType notificationType;

    @ManyToMany(mappedBy = "notificationTypes")
    private List<User> users;

}

