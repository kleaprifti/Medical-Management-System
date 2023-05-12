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
public class AppointmentController {

    private final AppointmentService appointmentService;


    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
    @GetMapping("")
    public Set<AppointmentDto> getAppointments(@RequestParam Long doctorId, @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime) {
        return appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);
    }
    @PostMapping("/add")
    public ResponseEntity<AppointmentDto> addAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto addedAppointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(addedAppointment, HttpStatus.CREATED);
    }

}