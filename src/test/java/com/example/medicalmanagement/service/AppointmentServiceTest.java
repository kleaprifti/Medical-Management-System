package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Role;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {
   private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceTest.class);
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void testGetAppointmentsBetweenDatesAndTimes() {
        LOGGER.info("Starting testGetAppointmentsBetweenDatesAndTimes");

        Long doctorId = 1L;
        Long patientId = null;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 20, 11, 0);

        Appointment appointment1 = new Appointment(1L, startDateTime, endDateTime, new User(), new User());
        Set<AppointmentDto> expected = new HashSet<>();
        AppointmentDto appointmentDto = new AppointmentDto(1L, startDateTime, endDateTime, doctorId, patientId);
        expected.add(appointmentDto);

        LOGGER.info("Setting up mock behavior");
        when(userRepository.existsById(doctorId)).thenReturn(true);
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime)).thenReturn(Arrays.asList(appointment1));
        when(modelMapper.map(appointment1, AppointmentDto.class)).thenReturn(appointmentDto);

        LOGGER.info("Calling method under test");
        Set<AppointmentDto> result = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);

        assertEquals(expected, result);

        LOGGER.info("Finished testGetAppointmentsBetweenDatesAndTimes");
    }
    @Test
    public void testAddAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setAppointmentDateStartTime(LocalDateTime.of(2023, 5, 15, 10, 0));
        appointmentDto.setAppointmentDateEndTime(LocalDateTime.of(2023, 5, 15, 11, 0));
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);

        User patient = new User();
        patient.setId(1L);
        patient.setFullName("Roberto Umbrella");
        Role patientRole = new Role();
        patientRole.setId(1L);
        patientRole.setUserRole(UserRole.PATIENT);
        patient.setRoles(List.of(patientRole));
        User doctor = new User();
        doctor.setId(2L);
        doctor.setFullName("Ana Mark");
        Role doctorRole = new Role();
        doctorRole.setId(2L);
        doctorRole.setUserRole(UserRole.DOCTOR);
        doctor.setRoles(List.of(doctorRole));

        when(userRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(userRepository.findByRolesUserRole(UserRole.PATIENT)).thenReturn(Collections.singletonList(patient));
        when(userRepository.findById(2L)).thenReturn(Optional.of(doctor));
        when(userRepository.findByRolesUserRole(UserRole.DOCTOR)).thenReturn(Collections.singletonList(doctor));
        when(appointmentRepository.findConflictingAppointments(anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.emptyList());
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment(1L, appointmentDto.getAppointmentDateStartTime(), appointmentDto.getAppointmentDateEndTime(), patient, doctor));

        AppointmentDto result = appointmentService.addAppointment(appointmentDto);

        assertNotNull(result);
        assertEquals(1L, result.getAppointmentId());
        assertEquals(LocalDateTime.of(2023, 5, 15, 10, 0), result.getAppointmentDateStartTime());
        assertEquals(LocalDateTime.of(2023, 5, 15, 11, 0), result.getAppointmentDateEndTime());

        verify(userRepository).findById(1L);
        verify(userRepository).findByRolesUserRole(UserRole.PATIENT);
        verify(userRepository).findById(2L);
        verify(userRepository).findByRolesUserRole(UserRole.DOCTOR);
        verify(appointmentRepository).findConflictingAppointments(1L, 2L, appointmentDto.getAppointmentDateStartTime(), appointmentDto.getAppointmentDateEndTime());
        verify(appointmentRepository).save(any(Appointment.class));
        LOGGER.info("Success testAddAppointment");

    }

}