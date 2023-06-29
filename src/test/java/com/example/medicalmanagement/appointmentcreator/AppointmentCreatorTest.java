package com.example.medicalmanagement.appointmentcreator;


import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AppointmentCreatorTest {
    @Mock
    private AppointmentDto appointmentDto;
    @Mock
    private User patient;
    @Mock
    private User doctor;
    @InjectMocks
    private AppointmentCreator appointmentCreator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAppointment() {
        // Create a mock Appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateStartTime(LocalDateTime.of(2023,06,22,9,0));
        appointment.setAppointmentDateEndTime(LocalDateTime.of(2023,06,22,10,0));
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        // Mock the behavior of appointmentDto
        when(appointmentDto.getAppointmentDateStartTime()).thenReturn(LocalDateTime.of(2023,06,22,9,0));
        when(appointmentDto.getAppointmentDateEndTime()).thenReturn(LocalDateTime.of(2023,06,22,10,0));

        // Call the method under test
        Appointment createdAppointment = appointmentCreator.createAppointment(appointmentDto, patient, doctor);

        // Assert the expected result
        assertEquals(appointment.getAppointmentDateStartTime(), createdAppointment.getAppointmentDateStartTime());
        assertEquals(appointment.getAppointmentDateEndTime(), createdAppointment.getAppointmentDateEndTime());
        assertEquals(appointment.getPatient(), createdAppointment.getPatient());
        assertEquals(appointment.getDoctor(), createdAppointment.getDoctor());
    }

    @Test
    void testCreateAppointmentDto() {
        // Create a mock AppointmentDto object
        AppointmentDto expectedDto = new AppointmentDto();
        expectedDto.setAppointmentId(1L);
        expectedDto.setAppointmentDateStartTime(LocalDateTime.of(2023,06,22,9,0));
        expectedDto.setAppointmentDateEndTime(LocalDateTime.of(2023,06,22,10,00));
        expectedDto.setDoctorId(2L);
        expectedDto.setPatientId(3L);

        // Create a mock Appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        appointment.setAppointmentDateStartTime(LocalDateTime.of(2023,06,22,9,0));
        appointment.setAppointmentDateEndTime(LocalDateTime.of(2023,06,22,10,00));

        User doctor = new User();
        doctor.setId(2L);

        User patient = new User();
        patient.setId(3L);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        // Call the method under test
        AppointmentDto createdDto = appointmentCreator.createAppointmentDto(appointment);

        // Assert the expected result
        assertEquals(expectedDto.getAppointmentId(), createdDto.getAppointmentId());
        assertEquals(expectedDto.getAppointmentDateStartTime(), createdDto.getAppointmentDateStartTime());
        assertEquals(expectedDto.getAppointmentDateEndTime(), createdDto.getAppointmentDateEndTime());
        assertEquals(expectedDto.getDoctorId(), createdDto.getDoctorId());
        assertEquals(expectedDto.getPatientId(), createdDto.getPatientId());
    }
}
