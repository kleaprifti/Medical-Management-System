package com.example.medicalmanagement.service;

import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.appointmentvalidator.AppointmentValidator;
import com.example.medicalmanagement.builder.AppointmentServiceBuilder;
import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.helpers.EmailContent;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AppointmentValidator appointmentValidator;
    private AppointmentCreator appointmentCreator;
    private EmailService emailService;

    @Autowired
    public AppointmentService(AppointmentServiceBuilder builder) {
        this.appointmentRepository = builder.getAppointmentRepository();
        this.appointmentCreator = builder.getAppointmentCreator();
        this.userRepository = builder.getUserRepository();
        this.modelMapper = builder.getModelMapper();
        this.appointmentValidator = builder.getAppointmentValidator();
        this.emailService = builder.getEmailService();
    }

    public Set<AppointmentDto> getAppointments(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {

       Optional<User> doctor = userRepository.findById(doctorId);
       if (doctor.isEmpty()) {
                throw  new NotFoundException("Doctor with Id " + doctorId + " was not found");
       }
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

    public void deleteAppointment(Long appointmentId, boolean wantNotification) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException("Appointment with ID " + appointmentId + " was not found"));


        appointmentRepository.delete(appointment);

        if(wantNotification) {
            User patient = appointment.getPatient();
            User doctor= appointment.getDoctor();
            String userEmail = patient.getContactInfo().getEmail();
            String cancellationEmailContent = EmailContent.generateEmail(patient.getFullName(),doctor.getFullName(), appointment.getAppointmentDateStartTime());

            String subject = "Cancellation of appointment";
            emailService.sendEmail(userEmail, subject, cancellationEmailContent);

        }
    }

}