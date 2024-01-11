package com.example.medicalmanagement.service;

public class AuthenticationResult {
    private boolean isAuthenticated;
    private Long userId;
    private String authToken;

    public AuthenticationResult(boolean isAuthenticated, Long userId, String authToken) {
        this.isAuthenticated = isAuthenticated;
        this.userId = userId;
        this.authToken = authToken;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public String getAuthToken() {
        return authToken;
    }
}
