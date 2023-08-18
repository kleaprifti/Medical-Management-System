package com.example.medicalmanagement.service;

import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.appointmentvalidator.AppointmentValidator;
import com.example.medicalmanagement.builder.AppointmentServiceBuilder;
import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AppointmentValidator appointmentValidator;

    @Mock
    private AppointmentCreator appointmentCreator;
    @Mock
    private AppointmentService appointmentService;
    @Mock
    private EmailService emailService;
    @InjectMocks
    private AppointmentServiceBuilder appointmentServiceBuilder;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentService = new AppointmentServiceBuilder(
                appointmentRepository,
                userRepository,
                modelMapper,
                appointmentValidator,
                appointmentCreator,
                emailService
        ).build();
    }

    @Test
    void getAppointments_ValidInput_ReturnsAppointments() {

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(2);

        User doctor = new User();
        when(userRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment());
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime)).thenReturn(appointments);

        AppointmentDto appointmentDto = new AppointmentDto();
        when(modelMapper.map(any(Appointment.class), eq(AppointmentDto.class))).thenReturn(appointmentDto);


        Set<AppointmentDto> result = appointmentService.getAppointments(doctorId, startDateTime, endDateTime);


        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findById(doctorId);
        verify(appointmentRepository, times(1)).findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime);
        verifyNoMoreInteractions(userRepository, appointmentRepository);
    }

    @Test
    void getAppointments_InvalidDoctorId_ThrowsNotFoundException() {

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(2);

        when(userRepository.findById(doctorId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> appointmentService.getAppointments(doctorId, startDateTime, endDateTime));
        verify(userRepository, times(1)).findById(doctorId);
        verifyNoMoreInteractions(userRepository, appointmentRepository);
    }

    @Test
    void getAppointments_NoAppointmentsFound_ThrowsNotFoundException() {

        Long doctorId = 1L;
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(2);

        User doctor = new User();
        when(userRepository.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime)).thenReturn(new ArrayList<>());

        assertThrows(NotFoundException.class, () -> appointmentService.getAppointments(doctorId, startDateTime, endDateTime));
        verify(userRepository, times(1)).findById(doctorId);
        verify(appointmentRepository, times(1)).findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(
                doctorId, endDateTime, startDateTime);
        verifyNoMoreInteractions(userRepository, appointmentRepository);
    }

    @Test
    void addAppointment_ValidInput_ReturnsAppointmentDto() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);

        User patient = new User();
        when(userRepository.findById(appointmentDto.getPatientId())).thenReturn(Optional.of(patient));

        User doctor = new User();
        when(userRepository.findById(appointmentDto.getDoctorId())).thenReturn(Optional.of(doctor));

        when(appointmentCreator.createAppointment(any(AppointmentDto.class), eq(patient), eq(doctor))).thenReturn(new Appointment());

        Appointment savedAppointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(savedAppointment);

        AppointmentDto savedAppointmentDto = new AppointmentDto();
        when(appointmentCreator.createAppointmentDto(savedAppointment)).thenReturn(savedAppointmentDto);


        AppointmentDto result = appointmentService.addAppointment(appointmentDto);

        assertNotNull(result);
        verify(appointmentValidator, times(1)).validate(appointmentDto);
        verify(userRepository, times(1)).findById(appointmentDto.getPatientId());
        verify(userRepository, times(1)).findById(appointmentDto.getDoctorId());
        verify(appointmentValidator, times(1)).checkSamePerson(patient, doctor);
        verify(appointmentCreator, times(1)).createAppointment(appointmentDto, patient, doctor);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        verify(appointmentCreator, times(1)).createAppointmentDto(savedAppointment);
        verifyNoMoreInteractions(appointmentValidator, userRepository, appointmentCreator, appointmentRepository);
    }

    @Test
    void addAppointment_InvalidPatientId_ThrowsNotFoundException() {

        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);

        when(userRepository.findById(appointmentDto.getPatientId())).thenReturn(Optional.empty());


        assertThrows(NotFoundException.class, () -> appointmentService.addAppointment(appointmentDto));
        verify(userRepository, times(1)).findById(appointmentDto.getPatientId());
        verifyNoMoreInteractions(userRepository, appointmentRepository);
    }

    @Test
    void addAppointment_InvalidDoctorId_ThrowsNotFoundException() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);

        User patient = new User();
        when(userRepository.findById(appointmentDto.getPatientId())).thenReturn(Optional.of(patient));

        when(userRepository.findById(appointmentDto.getDoctorId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appointmentService.addAppointment(appointmentDto));
        verify(userRepository, times(1)).findById(appointmentDto.getPatientId());
        verify(userRepository, times(1)).findById(appointmentDto.getDoctorId());
        verifyNoMoreInteractions(userRepository, appointmentRepository);
    }


    @Test
    void deleteAppointmentSuccess() {
        Long appointmentId = 123L;
        boolean wantNotification = true;

        Appointment appointment = mock(Appointment.class);
        when(appointment.getAppointmentId()).thenReturn(appointmentId);

        LocalDateTime appointmentDate = LocalDateTime.of(2023, 8, 16, 16, 10, 0); // Replace with appropriate date and time
        when(appointment.getAppointmentDateStartTime()).thenReturn(appointmentDate);
        LocalDateTime appointmentEndDate = LocalDateTime.of(2023, 8, 16, 17, 10, 0); // Replace with appropriate date and time
        when(appointment.getAppointmentDateEndTime()).thenReturn(appointmentDate);


        User patient = new User();
        User doctor = new User();

        when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));
        when(appointment.getPatient()).thenReturn(patient);
        when(appointment.getDoctor()).thenReturn(doctor);

        appointmentService.deleteAppointment(appointmentId, wantNotification);

        verify(appointmentRepository, times(1)).delete(appointment);

        if (wantNotification) {
            verify(emailService, times(1)).sendEmail(eq(patient.getEmail()), any(), any());

        } else {
            verify(emailService, never()).sendEmail(any(), any(), any());
        }
    }

    @Test
    void deleteAppointment_NotFound() {
        Long appointmentId = 1L;

        Mockito.when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appointmentService.deleteAppointment(appointmentId, true));
    }
}
