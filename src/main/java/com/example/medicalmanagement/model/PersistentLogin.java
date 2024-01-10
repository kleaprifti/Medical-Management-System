package com.example.medicalmanagement.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "persistent_logins")
public class PersistentLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String series;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date lastUsed;


    public String getToken() {
        return getToken();
    }

    public String getEmail() {
        return getEmail();
    }

    public void setSeries(String series) {
    }

    public void setToken(String tokenValue) {
    }

    public void setLastUsed(Date lastUsed) {
    }

    public void setUser(User user) {
    }

    public PersistentLogin getUser() {
        return  getUser();
    }
}
