//package com.example.medicalmanagement.service;
//import com.example.medicalmanagement.model.Appointment;
//import com.example.medicalmanagement.repository.AppointmentRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
// class AppointmentServiceTest {
//
//    @Mock
//    private AppointmentRepository appointmentRepository;
//
//    @InjectMocks
//    private AppointmentService appointmentService;
//
//    @Test
//     void testGetAppointmentsBetweenDatesAndTimes() {
//        Long doctorId = 1L;
//        LocalDateTime startDateTime = LocalDateTime.of(2023, 3, 30, 9, 0);
//        LocalDateTime endDateTime = LocalDateTime.of(2023, 3, 30, 12, 0);
//        Appointment appointment1 = new Appointment();
//        appointment1.setAppointmentId(2L);
//        Appointment appointment2 = new Appointment();
//        appointment2.setAppointmentId(3L);
//
//        List<Appointment> expectedAppointments = Arrays.asList(appointment1, appointment2);
//        Mockito.when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime)).thenReturn(expectedAppointments);
//
//        Set<Appointment> result = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);
//
//        Mockito.verify(appointmentRepository, times(1)).findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
//                ArgumentMatchers.eq(doctorId), ArgumentMatchers.any(LocalDateTime.class),
//                ArgumentMatchers.any(LocalDateTime.class));
//
//        assertEquals(2, result.size());
//        assertTrue(result.contains(appointment1));
//        assertTrue(result.contains(appointment2));
//
//
//        LOGGER.info("test is running....");
//        LOGGER.info("The list of the appointments is tested successfully with the expected output");
//    }
//}