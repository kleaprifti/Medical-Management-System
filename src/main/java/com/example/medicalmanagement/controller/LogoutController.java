package com.example.medicalmanagement.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@RequestMapping("/logout")
@CrossOrigin("localhost:4200/")
public class LogoutController {

    @GetMapping("")
    public ResponseEntity<String> logout(Authentication authentication) {
        if (authentication != null) {
            authentication.setAuthenticated(false);
        }
        return ResponseEntity.ok("Logged out successfully");

    }
//    @GetMapping
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//    HttpSession session = request.getSession(false);
//    if (session != null) {
//        session.invalidate();
//    }
//
//    return "redirect:/";
//}
}
