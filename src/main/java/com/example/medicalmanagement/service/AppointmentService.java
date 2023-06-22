package com.example.medicalmanagement.service;



import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.appointmentvalidator.AppointmentValidator;
import com.example.medicalmanagement.builder.AppointmentServiceBuilder;
import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.*;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service

public class AppointmentService {
    private  AppointmentRepository appointmentRepository;
    private  UserRepository userRepository;
    private  ModelMapper modelMapper;
    private AppointmentValidator appointmentValidator;
    private AppointmentCreator appointmentCreator;

    @Autowired
    public AppointmentService(AppointmentServiceBuilder builder) {
        this.appointmentRepository = builder.getAppointmentRepository();
        this.appointmentCreator = builder.getAppointmentCreator();
        this.userRepository = builder.getUserRepository();
        this.modelMapper = builder.getModelMapper();
        this.appointmentValidator = builder.getAppointmentValidator();
    }

    public Set<AppointmentDto> getAppointments(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        userRepository.findById(doctorId)
                .orElseThrow(() -> new NotFoundException("Doctor with Id " + doctorId + " was not found"));

        List<Appointment> currentAppointments;
        if (startDateTime != null && endDateTime != null) {
            currentAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime);
        } else {
            currentAppointments = appointmentRepository.findByDoctorId(doctorId);
        }
        if (currentAppointments.isEmpty()) {
            throw new NotFoundException("This doctor has no appointments at the given time");
        }

        return currentAppointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toSet());
    }


    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        appointmentValidator.validate(appointmentDto);

        User patient = userRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        User doctor = userRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        appointmentValidator.checkSamePerson(patient, doctor);

        Appointment appointment = appointmentCreator.createAppointment(appointmentDto, patient, doctor);
        Appointment savedAppointment = appointmentRepository.save(appointment);

        return appointmentCreator.createAppointmentDto(savedAppointment);
    }

}