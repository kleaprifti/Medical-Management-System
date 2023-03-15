package com.example.Medical.Management.System.service;

import com.example.Medical.Management.System.dto.AppointmentDto;
import com.example.Medical.Management.System.dto.PatientDto;
import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.repository.AppointmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

        private final AppointmentRepository appointmentRepository;

    private ModelMapper modelMapper;

    @Autowired
        public AppointmentService(AppointmentRepository appointmentRepository,ModelMapper modelMapper) {
            this.appointmentRepository = appointmentRepository;
            this.modelMapper=modelMapper;
        }




    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toList());
    }

    public AppointmentDto getAppointmentById(Long id) throws Exception {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Appointment not found with id: " + id));
        return modelMapper.map(appointment, AppointmentDto.class);
    }
    public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(appointment -> modelMapper.map(appointment, AppointmentDto.class))
                .collect(Collectors.toList());
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
}


