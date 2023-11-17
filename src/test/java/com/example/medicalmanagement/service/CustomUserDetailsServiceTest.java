package com.example.medicalmanagement.service;

import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.repository.ContactInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock
    private ContactInfoRepository contactInfoRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
     void loadUserByUsername() throws UsernameNotFoundException {
        String username = "test@example.com";
        ContactInfo mockContactInfo = new ContactInfo(1L, username, "password123", "1234567890", "slack123", null);

        when(contactInfoRepository.findByEmail(username)).thenReturn(mockContactInfo);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);


        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());

        verify(contactInfoRepository, times(1)).findByEmail(username);
    }
}