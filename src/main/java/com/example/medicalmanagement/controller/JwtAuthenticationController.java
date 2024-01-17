package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.security.JwtResponse;
import com.example.medicalmanagement.security.JwtTokenUtil;
import com.example.medicalmanagement.service.CustomUserDetailsService;
import com.example.medicalmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @GetMapping("/a")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginInfoDto authenticationRequest)  {
        boolean isAuthenticated = loginService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (isAuthenticated) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
