package com.example.medicalmanagement.appointmentvalidator;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.AlreadyExistsException;
import com.example.medicalmanagement.exceptionhandlers.DurationException;
import com.example.medicalmanagement.exceptionhandlers.SamePersonException;
import com.example.medicalmanagement.exceptionhandlers.TimeException;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@NoArgsConstructor
public class AppointmentValidator {
    private AppointmentRepository appointmentRepository;

    public AppointmentValidator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void validate(AppointmentDto appointmentDto) {
        validateAppointmentTime(appointmentDto.getAppointmentDateStartTime());
        checkConflictingAppointments(appointmentDto);
        validateDuration(appointmentDto.getAppointmentDateStartTime(), appointmentDto.getAppointmentDateEndTime());
    }

    void validateAppointmentTime(LocalDateTime appointmentDateTime) {
        LocalDateTime now = LocalDateTime.now();
        if (appointmentDateTime.isBefore(now)) {
            throw new TimeException("Appointment time cannot be in the past");
        }
    }

    void checkConflictingAppointments(AppointmentDto appointmentDto) {
        List<Appointment> conflictingAppointment = appointmentRepository.findConflictingAppointments(
                appointmentDto.getPatientId(), appointmentDto.getDoctorId(),
                appointmentDto.getAppointmentDateStartTime(), appointmentDto.getAppointmentDateEndTime());
        if (!conflictingAppointment.isEmpty()) {
            throw new AlreadyExistsException("This patient or this doctor has already an existing appointment during this time range");
        }
    }

    void validateDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (startDateTime.plusHours(1).isAfter(endDateTime) || startDateTime.isEqual(endDateTime)) {
            throw new DurationException("Appointment duration should be one hour");
        }
    }

    public void checkSamePerson(User patient, User doctor) {
        if (doctor.getId().equals(patient.getId())) {
            throw new SamePersonException("Doctor and patient cannot be the same person");
        }
    }

}
