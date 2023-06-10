package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.model.User;
import org.springframework.stereotype.Service;

@Service
public class AppointmentCreator {
    public Appointment createAppointment(AppointmentDto appointmentDto, User patient, User doctor) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateStartTime(appointmentDto.getAppointmentDateStartTime());
        appointment.setAppointmentDateEndTime(appointmentDto.getAppointmentDateEndTime());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        return appointment;
    }

    public AppointmentDto createAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setAppointmentId(appointment.getAppointmentId());
        appointmentDto.setAppointmentDateStartTime(appointment.getAppointmentDateStartTime());
        appointmentDto.setAppointmentDateEndTime(appointment.getAppointmentDateEndTime());
        appointmentDto.setDoctorId(appointment.getDoctor().getId());
        appointmentDto.setPatientId(appointment.getPatient().getId());
        return appointmentDto;
    }
}