package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.LoginInfoDto;
import com.example.medicalmanagement.service.LoginService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void successfulLogin() {
        LoginInfoDto loginInfoDto = new LoginInfoDto();
        loginInfoDto.setUsername("validUsername");
        loginInfoDto.setPassword("validPassword");
        when(loginService.authenticateUser(any(String.class), any(String.class))).thenReturn(true);

        ResponseEntity<Map<String, Object>> responseEntity = loginController.login(loginInfoDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login successful", responseEntity.getBody().get("message"));
    }

    @Test
    void failedLogin() {
        LoginInfoDto loginInfoDto = new LoginInfoDto();
        loginInfoDto.setUsername("invalidUsername");
        loginInfoDto.setPassword("invalidPassword");
        when(loginService.authenticateUser(any(String.class), any(String.class))).thenReturn(false);

        ResponseEntity<Map<String, Object>> responseEntity = loginController.login(loginInfoDto);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody().get("message"));
    }
}
