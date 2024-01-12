package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.AuthenticationResult;
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
//    @GetMapping("/user")
//    public ResponseEntity<Map<String, Object>> getUserInfo(
//            @RequestParam("token") String token,
//            @RequestParam("username") String username) {
//
//        Map<String, Object> response = new HashMap<>();
//
//        AuthenticationResult user = loginService.getUserInfoByAuthTokenAndUsername(token, username);
//
//        if (user != null && user.isAuthenticated()) {
//            response.put("isAuthenticated", true);
//            response.put("username", user.getUsername());
//            response.put("token", token);
//            response.put("message", "User information retrieved successfully");
//
//            return ResponseEntity.ok(response);
//        } else {
//            response.put("isAuthenticated", false);
//            response.put("message", "Invalid or expired token");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }



}
