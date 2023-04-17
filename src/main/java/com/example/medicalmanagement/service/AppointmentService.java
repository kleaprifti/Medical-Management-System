package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.Doctor;
import com.example.medicalmanagement.model.Patient;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.repository.DoctorRepository;
import com.example.medicalmanagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;

    }

    public Set<Appointment> getAppointmentsBetweenDatesAndTimes(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Set<Appointment> appointments = new HashSet<>();

        List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBeforeAndAppointmentDateEndTimeAfter(doctorId, endDateTime, startDateTime);

        appointments.addAll(currentAppointments);

        return appointments;
    }

    public Appointment addAppointment(AppointmentDto appointmentDto) {
        List<Appointment> existingAppointments = appointmentRepository.findAppointmentsByDoctorIdAndAppointmentDateStartTimeAndAndAppointmentDateEndTime(
                appointmentDto.getDoctorId(), appointmentDto.getAppointmentDateStartTime(), appointmentDto.getAppointmentDateEndTime());

        for (Appointment existingAppointment : existingAppointments) {
            if (existingAppointment.getAppointmentDateStartTime().isBefore(appointmentDto.getAppointmentDateEndTime()) &&
                    existingAppointment.getAppointmentDateEndTime().isAfter(appointmentDto.getAppointmentDateStartTime())) {

                throw new IllegalArgumentException("An appointment already exists during this time range");


            }
        }

        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setAppointmentDateStartTime(appointmentDto.getAppointmentDateStartTime());
        appointment.setAppointmentDateEndTime(appointmentDto.getAppointmentDateEndTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);

    }
}