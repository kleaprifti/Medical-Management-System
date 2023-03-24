package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.DoctorDto;
import com.example.medicalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
@Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public DoctorDto addDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);
    }

}
