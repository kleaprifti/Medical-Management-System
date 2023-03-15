package com.example.Medical.Management.System.service;

import com.example.Medical.Management.System.dto.AppointmentDto;
import com.example.Medical.Management.System.dto.DoctorDto;
import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.repository.AppointmentRepository;
import com.example.Medical.Management.System.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;


    @Autowired
    public DoctorService(DoctorRepository doctorRepository,AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository=appointmentRepository;
    }


    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // helper method to convert Doctor entity to DTO
    private DoctorDto convertToDto(Doctor doctor) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setProfile(doctor.getProfile());
        return doctorDto;
    }
    public DoctorDto addDoctor(DoctorDto doctorDto) {
        Doctor doctor = convertToEntity(doctorDto);
        doctor = doctorRepository.save(doctor);
        return convertToDto(doctor);
    }

    // helper method to convert DTO to Doctor entity
    private Doctor convertToEntity(DoctorDto doctorDto) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setProfile(doctorDto.getProfile());
        return doctor;
    }

    public AppointmentDto assignAppointmentToDoctor(Long doctorId, AppointmentDto appointmentDto) {
        Doctor doctor = doctorRepository.getOne(doctorId); // get the doctor by ID
        Appointment appointment = convertToEntity(appointmentDto);
        appointment.setDoctor(doctor); // set the doctor for the appointment
        appointment = appointmentRepository.save(appointment); // save the appointment
        return convertToDto(appointment);
    }

    // helper method to convert DTO to Appointment entity
    private Appointment convertToEntity(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        // set other fields in the appointment entity
        return appointment;
    }

    // helper method to convert Appointment entity to DTO
    private AppointmentDto convertToDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        // set fields in the appointment DTO
        return appointmentDto;
    }

}
