package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.exceptionhandlers.NotFoundException;
import com.example.medicalmanagement.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }


    @GetMapping("/{doctorId}")
    public Set<AppointmentDto> getAppointments(
            @PathVariable Long doctorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

        return appointmentService.getAppointments(doctorId, startDateTime, endDateTime);
    }


    @PostMapping("/add")
    public ResponseEntity<AppointmentDto> addAppointment(@RequestBody AppointmentDto appointmentDto) {
        AppointmentDto addedAppointment = appointmentService.addAppointment(appointmentDto);
        return new ResponseEntity<>(addedAppointment, HttpStatus.CREATED);
    }

}



