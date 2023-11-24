package com.example.medicalmanagement.service;

import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.repository.ContactInfoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private ContactInfoRepository contactInfoRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void authenticateUserWithValidCredentials() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail("test@example.com");
        contactInfo.setPassword("Password");
        when(contactInfoRepository.findByEmail("test@example.com")).thenReturn(contactInfo);

        when(passwordEncoder.matches("password12", "Password")).thenReturn(true);

        boolean result = loginService.authenticateUser("test@example.com", "password12");

        assertTrue(result);
    }

    @Test
    void authenticateUserWithInvalidUsername() {
        when(contactInfoRepository.findByEmail(anyString())).thenReturn(null);

        boolean result = loginService.authenticateUser("nonexistent@example.com", "password12");

        assertFalse(result);
    }

    @Test
    void authenticateUserWithInvalidPassword() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail("test@example.com");
        contactInfo.setPassword("password");
        when(contactInfoRepository.findByEmail("test@example.com")).thenReturn(contactInfo);

        when(passwordEncoder.matches("incorrectPassword", "password")).thenReturn(false);

        boolean result = loginService.authenticateUser("test@example.com", "incorrectPassword");

        assertFalse(result);
    }
}

