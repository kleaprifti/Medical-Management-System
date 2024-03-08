package com.example.medicalmanagement.service;

import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.validator.AppointmentValidator;
import com.example.medicalmanagement.builder.AppointmentServiceBuilder;
import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.helpers.EmailContent;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.UserDetails;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//import com.example.sharedlibrary.service.EmailService;

@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private UserDetailsRepository userDetailsRepository;
    private ModelMapper modelMapper;
    private AppointmentValidator appointmentValidator;
    private AppointmentCreator appointmentCreator;
//    private EmailService emailService;

    @Autowired
    public AppointmentService(AppointmentServiceBuilder builder) {
        this.appointmentRepository = builder.getAppointmentRepository();
        this.appointmentCreator = builder.getAppointmentCreator();
        this.userDetailsRepository = builder.getUserDetailsRepository();
        this.modelMapper = builder.getModelMapper();
        this.appointmentValidator = builder.getAppointmentValidator();
//        this.emailService = builder.getEmailService();
    }

    public Set<AppointmentDto> getAppointments(Long doctorId, Long patientId) {
        List<Appointment> currentAppointments;

        if (doctorId != null && patientId != null) {
            currentAppointments = appointmentRepository.findByDoctorIdAndPatientId(doctorId, patientId);
        } else if (doctorId != null) {
            currentAppointments = appointmentRepository.findByDoctorId(doctorId);
        } else if (patientId != null) {
            currentAppointments = appointmentRepository.findByPatientId(patientId);
        } else {
            currentAppointments = appointmentRepository.findAll();
        }

        return currentAppointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toSet());
    }

    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        appointmentValidator.validate(appointmentDto);

        UserDetails patient = userDetailsRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        UserDetails doctor = userDetailsRepository.findById(appointmentDto.getDoctorId())
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

        if (wantNotification) {
            UserDetails patient = appointment.getPatient();
            UserDetails doctor = appointment.getDoctor();
            String userEmail = patient.getUser().getEmail();
            String cancellationEmailContent = EmailContent.generateEmail(patient.getFullName(), doctor.getFullName(), appointment.getAppointmentDateStartTime());

            String subject = "Cancellation of appointment";
//            emailService.sendEmail(userEmail, subject, cancellationEmailContent);

        }
    }
}