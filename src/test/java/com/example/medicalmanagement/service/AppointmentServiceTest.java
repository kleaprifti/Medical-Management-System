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
    void deleteAppointment_NotFound() {
        Long appointmentId = 1L;

        Mockito.when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> appointmentService.deleteAppointment(appointmentId, true));
    }
    @Test
    public void getAppointments() {
        List<Appointment> sampleAppointments = new ArrayList<>();

        when(appointmentRepository.findByDoctorIdAndPatientId(anyLong(), anyLong()))
                .thenReturn(sampleAppointments);

        when(modelMapper.map(any(Appointment.class), eq(AppointmentDto.class)))
                .thenAnswer(invocation -> {
                    Appointment appointment = invocation.getArgument(0);

                    return new AppointmentDto();
                });

        Set<AppointmentDto> result1 = appointmentService.getAppointments(null, null);
        assertEquals(sampleAppointments.size(), result1.size());

        Set<AppointmentDto> result2 = appointmentService.getAppointments(1L, null);
        assertEquals(sampleAppointments.size(), result2.size());

        Set<AppointmentDto> result3 = appointmentService.getAppointments(null, 2L);
        assertEquals(sampleAppointments.size(), result3.size());

        Set<AppointmentDto> result4 = appointmentService.getAppointments(1L, 2L);
        assertEquals(sampleAppointments.size(), result4.size());
    }

}
