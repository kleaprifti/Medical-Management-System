package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginInfoDto loginInfoDto) {
        boolean isAuthenticated = loginService.authenticateUser(loginInfoDto.getUsername(), loginInfoDto.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", isAuthenticated);

        if (isAuthenticated) {
            response.put("username", loginInfoDto.getUsername());
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}