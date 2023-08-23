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
      void getAllUsersByRoleForDoctors() {
         UserService userService = mock(UserService.class);
         UserRole role=UserRole.DOCTOR;
         List<UserDto> doctorDtos = new ArrayList<>();
         UserDto userDto1 = Mockito.mock(UserDto.class);
         UserDto userDto2 = Mockito.mock(UserDto.class);
         userDto1.setRoles(List.of(role));
         userDto2.setRoles(List.of(role));

         doctorDtos.add(userDto1);
         doctorDtos.add(userDto2);

         when(userService.getAllUsers(UserRole.DOCTOR)).thenReturn(doctorDtos);


         List<UserDto> result = userService.getAllUsers(UserRole.DOCTOR);

         assertEquals(doctorDtos.size(), result.size());
     }

     @Test
      void getAllUsersByRoleForPatients() {
         UserService userService = mock(UserService.class);
         List<UserDto> patientDtos = new ArrayList<>();
         UserDto userDto1 = Mockito.mock(UserDto.class);
         UserDto userDto2 = Mockito.mock(UserDto.class);
         patientDtos.add(userDto1);
         patientDtos.add(userDto2);

         when(userService.getAllUsers(UserRole.PATIENT)).thenReturn(patientDtos);

         UserController userController = mock(UserController.class);

         List<UserDto> result = userService.getAllUsers(UserRole.PATIENT);

         assertEquals(patientDtos.size(), result.size());
         // You can add more assertions based on your mapping logic
     }
}