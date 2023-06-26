package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AppointmentControllerTest  {
     @Mock
     private AppointmentService appointmentService;

     @InjectMocks
     private AppointmentController appointmentController;

     @BeforeEach
     void setUp() {
         MockitoAnnotations.openMocks(this);
     }
    @Test
     void testGetAppointments() {
        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 1, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 6, 1, 12, 0);
        Set<AppointmentDto> appointments = new HashSet<>();

        when(appointmentService.getAppointments(eq(doctorId), eq(startDateTime), eq(endDateTime))).thenReturn(appointments);

        Set<AppointmentDto> result = appointmentController.getAppointments(doctorId, startDateTime, endDateTime);

        verify(appointmentService).getAppointments(doctorId, startDateTime, endDateTime);

        assertEquals(appointments, result);
    }

    @Test
     void testAddAppointment() {
         AppointmentDto appointmentDto = new AppointmentDto();
         AppointmentDto addedAppointment = new AppointmentDto();

         when(appointmentService.addAppointment(eq(appointmentDto))).thenReturn(addedAppointment);

         ResponseEntity<AppointmentDto> response = appointmentController.addAppointment(appointmentDto);

         verify(appointmentService).addAppointment(appointmentDto);

         assertEquals(HttpStatus.CREATED, response.getStatusCode());

         assertEquals(addedAppointment, response.getBody());
    }
}