package com.example.medicalmanagement.service;

import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.repository.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ContactInfoRepository contactInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateUser(String username, String password) {
        ContactInfo contactInfo = contactInfoRepository.findByEmail(username);

        if (contactInfo != null) {
            return passwordEncoder.matches(password, contactInfo.getPassword());
        }

        return false;
    }
}
