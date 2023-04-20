package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Doctor;
import com.example.medicalmanagement.model.Patient;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.DoctorRepository;
import com.example.medicalmanagement.repository.PatientRepository;
import static org.hibernate.sql.ast.SqlTreeCreationLogger.LOGGER;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

 class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testGetAppointmentsBetweenDatesAndTimes() {

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);
        Appointment appointment1 = new Appointment();
        Appointment appointment2 = new Appointment();
        Set<Appointment> expectedAppointments = new HashSet<>(Arrays.asList(appointment1, appointment2));

        Mockito.when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime))
                .thenReturn(Arrays.asList(appointment1, appointment2));

        Set<Appointment> actualAppointments = appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);

        Mockito.verify(appointmentRepository, times(1)).findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime);
        Assertions.assertEquals(expectedAppointments, actualAppointments);
        LOGGER.info("test is running....");
        LOGGER.info("The list of the appointments is tested successfully with the expected output");
    }



    @Test
     void testAddAppointment() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setAppointmentDateStartTime(LocalDateTime.of(2022, 5, 10, 9, 0));
        appointmentDto.setAppointmentDateEndTime(LocalDateTime.of(2022, 5, 10, 10, 0));
        appointmentDto.setDoctorId(1L);
        appointmentDto.setPatientId(1L);

        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("Anri");
        patient.setLastName("Duka");

        Doctor doctor = new Doctor();
        doctor.setId(1L);
        doctor.setFullName("Dr. Klejda");

        List<Appointment> existingAppointments = new ArrayList<>();
        Appointment existingAppointment = new Appointment();
        existingAppointment.setAppointmentId(1L);
        existingAppointment.setAppointmentDateStartTime(LocalDateTime.of(2022, 5, 10, 9, 30));
        existingAppointment.setAppointmentDateEndTime(LocalDateTime.of(2022, 5, 10, 10, 30));
        existingAppointment.setDoctor(doctor);
        existingAppointments.add(existingAppointment);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        Mockito.when(appointmentRepository.findAppointmentsByDoctorId(1L)).thenReturn(existingAppointments);
        Mockito.when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment());

        Assertions.assertThrows(IllegalArgumentException.class, () -> appointmentService.addAppointment(appointmentDto));
        LOGGER.info("test is running...");
        LOGGER.info("The new appointment is added successfully without any clashes and with 1 hour duration");
    }

}