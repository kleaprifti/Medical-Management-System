package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest  {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
     void getAllUsersByRole() {
        UserRole userRole = UserRole.DOCTOR; // Replace with the desired role
        List<UserDto> expectedUserDtos = new ArrayList<>();

        when(userService.getAllUsers(userRole)).thenReturn(expectedUserDtos);

        List<UserDto> actualUserDtos = userController.getAllUsersByRole(userRole);

        assertEquals(expectedUserDtos, actualUserDtos);
    }
}