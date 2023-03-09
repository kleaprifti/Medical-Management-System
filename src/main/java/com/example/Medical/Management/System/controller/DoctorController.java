package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.repository.DoctorRepository;
import com.example.Medical.Management.System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

}
