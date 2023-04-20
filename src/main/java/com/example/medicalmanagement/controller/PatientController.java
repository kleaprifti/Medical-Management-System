package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;

    }
    @GetMapping("/all")
    public List<PatientDto> getAllPatients(){
        return patientService.getAllPatients();
    }

}