package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("")
    public ResponseEntity<Set<AppointmentDto>> getAppointmentsForDoctorAndPatient(
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) Long patientId) {

        Set<AppointmentDto> appointments;

        if (doctorId != null && patientId != null) {
            appointments = appointmentService.getAppointments(doctorId, patientId);
        } else if (doctorId != null) {
            appointments = appointmentService.getAppointments(doctorId, null);
        } else if (patientId != null) {
            appointments = appointmentService.getAppointments(null, patientId);
        } else {
            appointments = appointmentService.getAllAppointments();
        }

        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AppointmentDto> addAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto addedAppointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(addedAppointment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long appointmentId,@RequestParam boolean wantNotification) {
        appointmentService.deleteAppointment(appointmentId,wantNotification);
        return ResponseEntity.ok().build();
    }
}