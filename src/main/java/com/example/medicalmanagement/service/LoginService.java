package com.example.medicalmanagement.service;

import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateUser(String username, String password) {
        User user= userRepository.findByEmail(username);

        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }

        return false;
    }
}
