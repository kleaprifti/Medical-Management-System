package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.service.AppointmentService;
import com.example.Medical.Management.System.service.DoctorService;
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
//    private final AppointmentService appointmentService;
//    private final PatientService patientService; // add this
//
////    private final DoctorService doctorService;
//
//
//    @Autowired
//    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService) {
//        this.appointmentService = appointmentService;
//        this.patientService = patientService;
////        this.doctorService = doctorService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
//        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
//        return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    } //  retrieve an Optional object that may or may not contain an Appointment object
//
//    @GetMapping
//    public List<Appointment> getAllAppointments() {
//        return appointmentService.getAllAppointments();
//    }
//
////    @GetMapping("/patient/{id}")
////    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long id) {
////        Optional<Patient> patient = patientService.getPatientById(id);
////        if(patient.isPresent()) {
////            return appointmentService.getAppointmentsByPatient(patient.get());
////        } else {
////            return Collections.emptyList();
////        }
////    }
//    @GetMapping("/patient/{id}")
//    public ResponseEntity<List<Appointment>> getAppointmentsByPatientId(@PathVariable Long id) {
//        Optional<Patient> patient = patientService.getPatientById(id);
//        if (patient.isPresent()) {
//            List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patient.get());
//            return ResponseEntity.ok(appointments);
//        }
//        return ResponseEntity.notFound().build();
//    }
////    public List<Appointment> getAppointmentsByPatientId(@PathVariable Long id) {
////        Optional<Patient> patient = patientService.getPatientById(id);
////        return patient.map(appointmentService::getAppointmentsByPatient).orElse(Collections.emptyList());
////    }
//
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
//
////    @PostMapping("/patient/{id}")
////    public ResponseEntity<Appointment> addAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
////        Optional<Patient> patient = patientService.getPatientById(id);
////        if(patient.isPresent()) {
////            appointment.setPatient(patient.get());
////            Appointment savedAppointment = appointmentService.saveAppointment(appointment);
////            return ResponseEntity.created(URI.create("/appointments/" + savedAppointment.getId())).body(savedAppointment);
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
////    @PostMapping("/assign")
////    public ResponseEntity<Appointment> assignAppointment(@RequestBody Appointment appointment, @RequestParam Long doctorId) {
////        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
////        if (doctor.isPresent()) {
////            appointment.setDoctor(doctor.get());
////            Appointment savedAppointment = appointmentService.saveAppointment(appointment);
////            return ResponseEntity.created(URI.create("/appointments/" + savedAppointment.getId())).body(savedAppointment);
////        } else {
////            return ResponseEntity.notFound().build();
////        }
////    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
//        appointmentService.deleteAppointment(id);
//        return ResponseEntity.noContent().build();
//    }
//
////    @GetMapping("/doctor/{id}")
////    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
////        return appointmentService.findByDoctorId(doctorId);
////    }

}


