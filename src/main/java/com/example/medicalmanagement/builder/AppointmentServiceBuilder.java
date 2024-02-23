package com.example.medicalmanagement.builder;

import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserDetailsRepository;
import com.example.medicalmanagement.appointmentcreator.AppointmentCreator;
import com.example.medicalmanagement.service.AppointmentService;
import com.example.medicalmanagement.validator.AppointmentValidator;
//import com.example.sharedlibrary.service.EmailService;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
@Service
@Getter
public class AppointmentServiceBuilder {
    private AppointmentRepository appointmentRepository;
    private UserDetailsRepository userDetailsRepository;
    private ModelMapper modelMapper;
    private AppointmentValidator appointmentValidator;
    private AppointmentCreator appointmentCreator;
//    private final EmailService emailService;
    public AppointmentServiceBuilder(AppointmentRepository appointmentRepository, UserDetailsRepository userDetailsRepository, ModelMapper modelMapper, AppointmentValidator appointmentValidator, AppointmentCreator appointmentCreator) {
        this.appointmentRepository = appointmentRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.modelMapper = modelMapper;
        this.appointmentValidator = appointmentValidator;
        this.appointmentCreator = appointmentCreator;
//        this.emailService=emailService;
    }
    public AppointmentServiceBuilder setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        return this;
    }
    public AppointmentServiceBuilder setUserDetailsRepository(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
        return this;
    }
    public AppointmentServiceBuilder setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        return this;
    }
    public AppointmentServiceBuilder setAppointmentValidator(AppointmentValidator appointmentValidator) {
        this.appointmentValidator = appointmentValidator;
        return this;
    }
    public AppointmentServiceBuilder setAppointmentCreator(AppointmentCreator appointmentCreator) {
        this.appointmentCreator = appointmentCreator;
        return this;
    }
    public AppointmentService build() {
        return new AppointmentService(this);
    }
}