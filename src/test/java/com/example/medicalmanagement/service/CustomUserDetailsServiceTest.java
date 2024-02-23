package com.example.medicalmanagement.service;

import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.ContactInfo;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.ContactInfoRepository;
import com.example.medicalmanagement.repository.UserRepository;
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
    private UserRepository contactInfoRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
     void loadUserByUsername() throws UsernameNotFoundException {
        String username = "test@example.com";
        User mockContactInfo = new User();
        mockContactInfo.setPassword("password123");
        mockContactInfo.setEmail(username);
        mockContactInfo.setRole("USER");
        when(contactInfoRepository.findByEmail(username)).thenReturn(mockContactInfo);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(mockContactInfo.getEmail());


        assertEquals(username, userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());

        verify(contactInfoRepository, times(1)).findByEmail(username);
    }
}