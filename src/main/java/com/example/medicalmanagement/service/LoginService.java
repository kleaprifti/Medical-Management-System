package com.example.medicalmanagement.service;

import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResult authenticateUser(String username, String password, boolean rememberMe) {
        User user = userRepository.findByEmail(username);

        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                String authToken = rememberMe ? generateAuthToken(user.getAuthToken()) : null;
                return new AuthenticationResult(true, user.getId(), authToken);
            }
        }

        return new AuthenticationResult(false, user.getId(), user.getAuthToken());
    }


    private String generateAuthToken(String userId) {
        return UUID.randomUUID().toString();
    }


//    public AuthenticationResult getUserInfoByAuthToken(String token) {
//        return getUserInfoByAuthToken(token);
//    }
//
//    public AuthenticationResult getUserInfoByAuthTokenAndUsername(String token, String username) {
//        User user = userRepository.findByTokenAndUsername(token, username);
//
//        if (user != null) {
//            return new AuthenticationResult(true, user.getUsername(), user.getAuthToken());
//        } else {
//            return new AuthenticationResult(false, null, null);
//        }
//    }
}