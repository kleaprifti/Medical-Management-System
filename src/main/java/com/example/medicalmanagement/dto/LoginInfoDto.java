package com.example.medicalmanagement.dto;

import lombok.*;

@Data
public class LoginInfoDto {

    private String username;
    private String password;
    public LoginInfoDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
