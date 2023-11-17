package com.example.medicalmanagement.service;

import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ContactInfoRepository contactInfo;

    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        ContactInfo userCredentials = contactInfo.findByEmail(username);
        if (userCredentials == null) {
            throw new NotFoundException("User not found with email: " + username);
        }
        return User.builder().username(userCredentials.getEmail()).password(userCredentials.getPassword()).roles("USER").build();
    }
}
