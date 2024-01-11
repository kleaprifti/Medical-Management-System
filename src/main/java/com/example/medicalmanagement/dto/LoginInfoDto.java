package com.example.medicalmanagement.dto;

import lombok.Data;

@Data
public class LoginInfoDto {
    private String username;
    private String password;
    private boolean rememberMe;

}
