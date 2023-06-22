package com.example.medicalmanagement.appointmentvalidator;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.AlreadyExistsException;
import com.example.medicalmanagement.exceptionhandlers.DurationException;
import com.example.medicalmanagement.exceptionhandlers.SamePersonException;
import com.example.medicalmanagement.exceptionhandlers.TimeException;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AppointmentValidatorTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    private AppointmentValidator appointmentValidator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        appointmentValidator = new AppointmentValidator(appointmentRepository);
    }

    @Test
    void validateAppointmentTime_validTime_noExceptionThrown() {
        LocalDateTime appointmentDateTime = LocalDateTime.now().plusMinutes(30); // 30 minutes in the future

        appointmentValidator.validateAppointmentTime(appointmentDateTime);

        // No exception should be thrown
    }

    @Test
    void validateAppointmentTime_pastTime_exceptionThrown() {
        LocalDateTime appointmentDateTime = LocalDateTime.now().minusMinutes(30); // 30 minutes in the past

        assertThrows(TimeException.class, () -> appointmentValidator.validateAppointmentTime(appointmentDateTime));
    }

    @Test
    void checkConflictingAppointments_noConflicts_noExceptionThrown() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);
        appointmentDto.setAppointmentDateStartTime(LocalDateTime.now().plusHours(1));
        appointmentDto.setAppointmentDateEndTime(LocalDateTime.now().plusHours(2));

        when(appointmentRepository.findConflictingAppointments(anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(new ArrayList<>());

        appointmentValidator.checkConflictingAppointments(appointmentDto);

        // No exception should be thrown
        verify(appointmentRepository, times(1)).findConflictingAppointments(anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void checkConflictingAppointments_conflictExists_exceptionThrown() {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setPatientId(1L);
        appointmentDto.setDoctorId(2L);
        appointmentDto.setAppointmentDateStartTime(LocalDateTime.now().plusHours(1));
        appointmentDto.setAppointmentDateEndTime(LocalDateTime.now().plusHours(2));

        List<Appointment> conflictingAppointments = new ArrayList<>();
        conflictingAppointments.add(new Appointment());

        when(appointmentRepository.findConflictingAppointments(anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(conflictingAppointments);

        assertThrows(AlreadyExistsException.class, () -> appointmentValidator.checkConflictingAppointments(appointmentDto));

        verify(appointmentRepository, times(1)).findConflictingAppointments(anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    void validateDuration_validDuration_noExceptionThrown() {
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusHours(1); // 1-hour duration

        appointmentValidator.validateDuration(startDateTime, endDateTime);

        // No exception should be thrown
    }

    @Test
    void validateDuration_invalidDuration_exceptionThrown() {
        LocalDateTime startDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = startDateTime.plusMinutes(30); // Less than 1-hour duration

        assertThrows(DurationException.class, () -> appointmentValidator.validateDuration(startDateTime, endDateTime));
    }

    @Test
    void checkSamePerson_differentPersons_noExceptionThrown() {
        User patient = new User();
        patient.setId(1L);

        User doctor = new User();
        doctor.setId(2L);

        appointmentValidator.checkSamePerson(patient, doctor);

        // No exception should be thrown
    }

    @Test
    void checkSamePerson_samePerson_exceptionThrown() {
        User user = new User();
        user.setId(1L);

        assertThrows(SamePersonException.class, () -> appointmentValidator.checkSamePerson(user, user));
    }
}
