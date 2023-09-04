package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @GetMapping("/doctor/{doctorId}")
    public Set<AppointmentDto> getAppointmentsForDoctor(
            @PathVariable Long doctorId,
            @RequestParam(required = false) Long patientId,
            @RequestParam(required = false) String startDateTime,
            @RequestParam(required = false) String endDateTime) {

        LocalDateTime start = (startDateTime != null) ? LocalDateTime.parse(startDateTime) : null;
        LocalDateTime end = (endDateTime != null) ? LocalDateTime.parse(endDateTime) : null;

        return appointmentService.getAppointments(doctorId, patientId, start, end);
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



