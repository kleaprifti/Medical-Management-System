package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.service.AppointmentService;
import com.example.Medical.Management.System.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final PatientService patientService; // add this


    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    } //  retrieve an Optional object that may or may not contain an Appointment object

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

//    @GetMapping("/patient/{id}")
//    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long id) {
//        Optional<Patient> patient = patientService.getPatientById(id);
//        return patient.map(appointmentService::getAppointmentsByPatient).orElse(Collections.emptyList());
//    }

//    @GetMapping(params = {"start", "end"})
//    public List<Appointment> getAppointmentsBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
//                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
//        return appointmentService.getAppointmentsBetweenDates(start, end);
//    }
//
//    @PostMapping
//    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
//        Appointment savedAppointment = appointmentService.saveAppointment(appointment);
//        return ResponseEntity.created(URI.create("/appointments/" + savedAppointment.getId())).body(savedAppointment);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}


