package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppointmentServiceTest {
   private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceTest.class);
    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void testGetAppointmentsBetweenDatesAndTimes() {
        LOGGER.info("Starting testGetAppointmentsBetweenDatesAndTimes");
        Long doctorId = 1L;
        Long patientId=null;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 20, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 20, 11, 0);
        Appointment appointment1 = new Appointment(1L, startDateTime, endDateTime, new User(), new User());
        List<Appointment> appointmentList = Arrays.asList(appointment1);
        Set<AppointmentDto> expected = new HashSet<>();
        AppointmentDto appointmentDto = new AppointmentDto(1L, startDateTime, endDateTime,doctorId,patientId);
        expected.add(appointmentDto);

        LOGGER.info("Setting up mock behavior");

        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime)).thenReturn(appointmentList);

        when(modelMapper.map(appointment1, AppointmentDto.class)).thenReturn(appointmentDto);
        LOGGER.info("Calling method under test");

        Set<AppointmentDto> result = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);

        assertEquals(expected, result);

        LOGGER.info("Finished testGetAppointmentsBetweenDatesAndTimes");
    }

    }