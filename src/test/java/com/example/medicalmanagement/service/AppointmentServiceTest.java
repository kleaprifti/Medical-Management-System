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
        // set up test data
        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 10, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 1, 1, 12, 0);
        Appointment appointment1 = new Appointment(1L, startDateTime, endDateTime, new User(), new User());
        List<Appointment> appointmentList = Arrays.asList(appointment1);
        Set<AppointmentDto> expected = new HashSet<>();
        AppointmentDto appointmentDto = new AppointmentDto(1L, startDateTime, endDateTime);
        expected.add(appointmentDto);

        // set up mock behavior
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime)).thenReturn(appointmentList);

        when(modelMapper.map(appointment1, AppointmentDto.class)).thenReturn(appointmentDto);

        // call method under test
        Set<AppointmentDto> result = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);

        // assert result
        assertEquals(expected, result);


        LOGGER.info("The list of the appointments is tested successfully with the expected output");
    }
}
