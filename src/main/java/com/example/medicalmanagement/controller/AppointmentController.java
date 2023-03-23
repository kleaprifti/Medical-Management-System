package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    private final AppointmentRepository appointmentRepository;


    @Autowired
    public AppointmentController(AppointmentService appointmentService,AppointmentRepository appointmentRepository) {
        this.appointmentService = appointmentService;
        this.appointmentRepository = appointmentRepository;
    }
    @GetMapping("")
    public List<Appointment> getAppointmentsBetweenDatesAndTimes(@RequestParam Long doctorId, @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime) {

        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);

        return appointments;
    }



    @GetMapping("/{id}")
    public AppointmentDto getAppointmentById(@PathVariable Long id) throws Exception {
        return appointmentService.getAppointmentById(id);
    }
    @PostMapping
    public AppointmentDto addAppointment(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.addAppointment(appointmentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) throws Exception {
        appointmentService.deleteAppointment(id);
    }
    @GetMapping("/patient/{id}")
    public List<AppointmentDto> getAppointmentsByPatientId(@PathVariable Long id) {
        return appointmentService.getAppointmentsByPatientId(id);
    }
}
