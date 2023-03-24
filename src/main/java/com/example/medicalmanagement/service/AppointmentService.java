package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private ModelMapper modelMapper;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
    }


    public AppointmentDto getAppointmentById(Long id) throws Exception {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment not found with id: " + id));
        return modelMapper.map(appointment, AppointmentDto.class);
    }



    public AppointmentDto addAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = modelMapper.map(appointmentDto, Appointment.class);
        appointment.setId(null);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return modelMapper.map(savedAppointment, AppointmentDto.class);
    }

    public void deleteAppointment(Long id) throws Exception {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment not found with id: " + id));
        appointmentRepository.delete(appointment);
    }




    public List<Appointment> getAppointmentsBetweenDatesAndTimes(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);
    }

}


