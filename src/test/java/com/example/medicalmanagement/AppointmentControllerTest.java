package com.example.medicalmanagement;

import com.example.medicalmanagement.controller.AppointmentController;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

public class AppointmentControllerTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAppointmentsBetweenDatesAndTimesTest() {
        // Create test data
        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 28, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 28, 12, 0);

        // Mock the behavior of the appointmentRepository
        List<Appointment> appointments1 = new ArrayList<>();
        appointments1.add(new Appointment());
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(doctorId, startDateTime, startDateTime.plusHours(1)))
                .thenReturn(appointments1);

        List<Appointment> appointments2 = new ArrayList<>();
        appointments2.add(new Appointment());
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(doctorId, startDateTime.plusHours(1), startDateTime.plusHours(2)))
                .thenReturn(appointments2);

        List<Appointment> appointments3 = new ArrayList<>();
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(doctorId, startDateTime.plusHours(2), endDateTime))
                .thenReturn(appointments3);

        // Call the method under test
        Set<Appointment> result = appointmentController.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);

        // Verify the results
        Assertions.assertEquals(2, result.size());
    }
}


