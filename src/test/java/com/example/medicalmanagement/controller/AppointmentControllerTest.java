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

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
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
     void addAppointment() {
         AppointmentDto appointmentDto = new AppointmentDto();
         AppointmentDto addedAppointment = new AppointmentDto();

         when(appointmentService.addAppointment(appointmentDto)).thenReturn(addedAppointment);

         ResponseEntity<AppointmentDto> response = appointmentController.addAppointment(appointmentDto);

         verify(appointmentService).addAppointment(appointmentDto);

         assertEquals(HttpStatus.CREATED, response.getStatusCode());

         assertEquals(addedAppointment, response.getBody());
    }

    @Test
    public void getAppointmentsForDoctorAndPatient() {
        Set<AppointmentDto> sampleAppointments = new HashSet<>();

        when(appointmentService.getAppointments(anyLong(), anyLong()))
                .thenReturn(sampleAppointments);

        ResponseEntity<Set<AppointmentDto>> responseEntity = appointmentController
                .getAppointmentsForDoctorAndPatient(1L, 2L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Set<AppointmentDto> resultAppointments = responseEntity.getBody();
        assertEquals(sampleAppointments.size(), resultAppointments.size());
    }
}