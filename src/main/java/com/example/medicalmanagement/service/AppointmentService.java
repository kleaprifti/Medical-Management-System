package com.example.medicalmanagement.service;

import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;


    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;

    }


    public Set<Appointment> getAppointmentsBetweenDatesAndTimes(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Set<Appointment> appointments = new HashSet<>();
        LocalDateTime currentDateTime = startDateTime.truncatedTo(ChronoUnit.HOURS);

        while (currentDateTime.isBefore(endDateTime)) {
            LocalDateTime appointmentEndDateTime = currentDateTime.plusHours(1);

            List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(doctorId, currentDateTime, appointmentEndDateTime);

            appointments.addAll(currentAppointments);

            currentDateTime = currentDateTime.plusHours(1);
        }

        return appointments;
    }






}


