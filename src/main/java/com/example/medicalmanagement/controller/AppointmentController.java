package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Set<Appointment> getAppointmentsBetweenDatesAndTimes(@RequestParam Long doctorId, @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime) {

        return appointmentService.getAppointmentsBetweenDatesAndTimes(doctorId, startDateTime, endDateTime);
    }

}