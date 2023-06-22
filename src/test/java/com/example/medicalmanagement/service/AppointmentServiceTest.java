package com.example.medicalmanagement.service;

import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.appointmentvalidator.AppointmentValidator;
import com.example.medicalmanagement.builder.AppointmentServiceBuilder;
import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceTest.class);

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AppointmentValidator appointmentValidator;

    @Mock
    private AppointmentCreator appointmentCreator;

    private AppointmentService appointmentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Configure appointmentServiceBuilder mock
        AppointmentServiceBuilder appointmentServiceBuilder = new AppointmentServiceBuilder(
                appointmentRepository,
                userRepository,
                modelMapper,
                appointmentValidator,
                appointmentCreator
        );

        appointmentService = new AppointmentService(appointmentServiceBuilder);
    }

    @Test
    public void getAppointments_ValidDoctorIdAndTime_ReturnsAppointments() {
        LOGGER.info("Starting testGetAppointmentsBetweenDatesAndTimes");

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 6, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 6, 1, 12, 0);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        LOGGER.info("Setting up mock behavior");

        // Mocking userRepository.findById
        when(userRepository.findById(doctorId)).thenReturn(Optional.of(new User()));
        // Mocking appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime))
                .thenReturn(Collections.singletonList(appointment));
        // Mocking modelMapper.map
        when(modelMapper.map(appointment, AppointmentDto.class)).thenReturn(new AppointmentDto());
        LOGGER.info("Calling method under test");

        Set<AppointmentDto> appointments = appointmentService.getAppointments(doctorId, startDateTime, endDateTime);

        assertFalse(appointments.isEmpty());
        assertEquals(1, appointments.size());
        // Verify that userRepository.findById is called once
        verify(userRepository, times(1)).findById(doctorId);
        // Verify that appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter is called once
        verify(appointmentRepository, times(1))
                .findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime);
        // Verify that modelMapper.map is called once
        verify(modelMapper, times(1)).map(appointment, AppointmentDto.class);
    }

    @Test
    public void addAppointment_ValidAppointmentDto_ReturnsCreatedAppointment() {
        LOGGER.info("Success testAddAppointment");

        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);
        User patient = new User();
        User doctor = new User();
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1L);
        // Mocking userRepository.findById
        when(userRepository.findById(appointmentDto.getPatientId())).thenReturn(Optional.of(patient));
        when(userRepository.findById(appointmentDto.getDoctorId())).thenReturn(Optional.of(doctor));
        // Mocking appointmentValidator.validate
        doNothing().when(appointmentValidator).validate(appointmentDto);
        // Mocking appointmentValidator.checkSamePerson
        doNothing().when(appointmentValidator).checkSamePerson(patient, doctor);
        // Mocking appointmentCreator.createAppointment
        when(appointmentCreator.createAppointment(appointmentDto, patient, doctor)).thenReturn(appointment);
        // Mocking appointmentRepository.save
        when(appointmentRepository.save(appointment)).thenReturn(appointment);
        // Mocking appointmentCreator.createAppointmentDto
        when(appointmentCreator.createAppointmentDto(appointment)).thenReturn(appointmentDto);

        AppointmentDto createdAppointment = appointmentService.addAppointment(appointmentDto);

        assertNotNull(createdAppointment);
        assertEquals(appointmentDto, createdAppointment);
        // Verify that userRepository.findById is called twice
        verify(userRepository, times(2)).findById(anyLong());
        // Verify that appointmentValidator.validate is called once
        verify(appointmentValidator, times(1)).validate(appointmentDto);
        // Verify that appointmentValidator.checkSamePerson is called once
        verify(appointmentValidator, times(1)).checkSamePerson(patient, doctor);
        // Verify that appointmentCreator.createAppointment is called once
        verify(appointmentCreator, times(1)).createAppointment(appointmentDto, patient, doctor);
        // Verify that appointmentRepository.save is called once
        verify(appointmentRepository, times(1)).save(appointment);
        // Verify that appointmentCreator.createAppointmentDto is called once
        verify(appointmentCreator, times(1)).createAppointmentDto(appointment);
    }
}

