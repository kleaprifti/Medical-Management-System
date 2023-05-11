package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    public Set<AppointmentDto> getAppointmentsBetweenDatesAndTimes(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime);
        return currentAppointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toSet());
    }


    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        try {
            LocalDateTime now = LocalDateTime.now();
            if (appointmentDto.getAppointmentDateStartTime().isBefore(now)) {
                throw new IllegalArgumentException("Appointment time cannot be in the past");
            }

            List<Appointment> conflictingAppointment = appointmentRepository.findConflictingAppointments(appointmentDto.getPatientId(),appointmentDto.getDoctorId(),appointmentDto.getAppointmentDateStartTime(),appointmentDto.getAppointmentDateEndTime());
            if (!conflictingAppointment.isEmpty()){
                throw new IllegalArgumentException("This patient or this doctor has already an existing appointment during this time range");
            }
            LocalDateTime startDateTime = appointmentDto.getAppointmentDateStartTime();
            LocalDateTime endDateTime = appointmentDto.getAppointmentDateEndTime();
            if (startDateTime.plusHours(1).isAfter(endDateTime) || startDateTime.isEqual(endDateTime) || startDateTime.plusHours(1).isBefore(endDateTime)) {
                throw new IllegalArgumentException("Appointment duration should be one hour");
            }
            User patient = userRepository.findById(appointmentDto.getPatientId())
                    .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
            List<User> usersList = userRepository.findByRolesUserRole(UserRole.PATIENT);
            if(!usersList.contains(patient)){
                throw new IllegalArgumentException("Patient not found");
            }
            User doctor = userRepository.findById(appointmentDto.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            usersList = userRepository.findByRolesUserRole(UserRole.DOCTOR);
            if(!usersList.contains(doctor)){
                throw new IllegalArgumentException("Doctor not found");
            }
            Appointment appointment = new Appointment();
            appointment.setAppointmentDateStartTime(startDateTime);
            appointment.setAppointmentDateEndTime(endDateTime);
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            Appointment savedAppointment = appointmentRepository.save(appointment);

            AppointmentDto savedAppointmentDto = new AppointmentDto();
            savedAppointmentDto.setAppointmentId(savedAppointment.getAppointmentId());
            savedAppointmentDto.setAppointmentDateStartTime(savedAppointment.getAppointmentDateStartTime());
            savedAppointmentDto.setAppointmentDateEndTime(savedAppointment.getAppointmentDateEndTime());
            savedAppointmentDto.setDoctorId(savedAppointment.getDoctor().getId());
            savedAppointmentDto.setPatientId(savedAppointment.getPatient().getId());

            return savedAppointmentDto;
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Technical issue occurred while adding appointment", e);
        }
    }
}