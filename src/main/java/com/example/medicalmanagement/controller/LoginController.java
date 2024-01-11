package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.AuthenticationResult;
import com.example.medicalmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginInfoDto loginInfoDto,
                                                     @RequestParam(value = "rememberMe", defaultValue = "false") boolean rememberMe) {
        AuthenticationResult authenticationResult = loginService.authenticateUser(loginInfoDto.getUsername(), loginInfoDto.getPassword(), rememberMe);

        Map<String, Object> response = new HashMap<>();
        response.put("isAuthenticated", authenticationResult.isAuthenticated());

        if (authenticationResult.isAuthenticated()) {
            response.put("username", loginInfoDto.getUsername());
            response.put("message", "Login successful");

            if (rememberMe) {
                response.put("token", authenticationResult.getAuthToken());
            }

            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
