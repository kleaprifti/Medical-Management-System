package com.example.medicalmanagement.builder;

import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.UserRepository;
import com.example.medicalmanagement.service.AppointmentCreator;
import com.example.medicalmanagement.service.AppointmentService;
import com.example.medicalmanagement.service.AppointmentValidator;
import org.modelmapper.ModelMapper;

public class AppointmentServiceBuilder {
    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AppointmentValidator appointmentValidator;
    private AppointmentCreator appointmentCreator;

    public AppointmentServiceBuilder setAppointmentRepository(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        return this;
    }

    public AppointmentServiceBuilder setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public AppointmentService createAppointmentService() {
        return new AppointmentService(appointmentRepository, userRepository, modelMapper, appointmentValidator, appointmentCreator);
    }
}