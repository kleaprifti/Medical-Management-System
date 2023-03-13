package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.repository.DoctorRepository;
import com.example.Medical.Management.System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @GetMapping("")
//    public List<Doctor> getAllDoctors() {
//        return doctorRepository.findAll();
//    }
//    @PostMapping
//    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
//        Doctor savedDoctor = doctorRepository.save(doctor);
//        return ResponseEntity.created(URI.create("/doctors/" + savedDoctor.getId())).body(savedDoctor);
//    }

}
