package com.example.medicalmanagement.service;
import static org.mockito.Mockito.*;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;

@SpringBootTest
 class AppointmentServiceTest {

    @MockBean
    private AppointmentRepository appointmentRepository;
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(AppointmentServiceTest.class));

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetAppointmentsBetweenDatesAndTimes() {

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 30, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 30, 12, 0);

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentId(2L);
        appointment1.setAppointmentDateStartTime(LocalDateTime.of(2023, 3, 30, 9, 0));
        appointment1.setAppointmentDateEndTime(LocalDateTime.of(2023, 3, 30, 10, 0));

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentId(3L);
        appointment2.setAppointmentDateStartTime(LocalDateTime.of(2023, 3, 30, 10, 0));
        appointment2.setAppointmentDateEndTime(LocalDateTime.of(2023, 3, 30, 11, 0));

        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        Set<Appointment> expectedAppointments = new HashSet<>(appointments);

        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(ArgumentMatchers.eq(doctorId),
                ArgumentMatchers.any(LocalDateTime.class), ArgumentMatchers.any(LocalDateTime.class))).thenReturn(appointments);

        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        Set<Appointment> result = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime,
                endDateTime);

        verify(appointmentRepository, times(3)).findByDoctorIdAndAppointmentDateStartTimeBetween(
                ArgumentMatchers.eq(doctorId), ArgumentMatchers.any(LocalDateTime.class),
                ArgumentMatchers.any(LocalDateTime.class));
        Assertions.assertEquals(expectedAppointments, result);

        LOGGER.warning("OK");
        LOGGER.info("Input values - doctorId: {}, startDateTime: {}, endDateTime: {}");
        LOGGER.info("Expected output: {}");
        LOGGER.info("Actual output: {}");
    }
}