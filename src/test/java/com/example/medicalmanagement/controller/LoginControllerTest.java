package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void loginWithValidCredentials() {
        LoginInfoDto loginInfoDto = new LoginInfoDto("romeisaaliu1@gmail.com", "password");
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(loginService.authenticateUser("romeisaaliu1@gmail.com","password")).thenReturn(true);

        ResponseEntity<Map<String, Object>> responseEntity = loginController.login(loginInfoDto, false, request, response);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue((boolean) responseEntity.getBody().get("isAuthenticated"));
        assertEquals("Login successful", responseEntity.getBody().get("message"));
    }

    @Test
    void loginWithInvalidCredentials() {
        LoginInfoDto loginInfoDto = new LoginInfoDto("invalidUsername", "invalidPassword");
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        when(loginService.authenticateUser("invalidUsername", "invalidPassword")).thenReturn(false);

        ResponseEntity<Map<String, Object>> responseEntity = loginController.login(loginInfoDto, false, request, response);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertFalse((boolean) responseEntity.getBody().get("isAuthenticated"));
        assertEquals("Invalid credentials", responseEntity.getBody().get("message"));
    }
}
