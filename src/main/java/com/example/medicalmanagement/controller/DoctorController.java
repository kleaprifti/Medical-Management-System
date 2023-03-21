package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.dto.DoctorDto;
import com.example.medicalmanagement.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
@Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping
    public DoctorDto addDoctor(@RequestBody DoctorDto doctorDto) {
        return doctorService.addDoctor(doctorDto);
    }

    @PostMapping("/{doctorId}/appointments")
    public AppointmentDto assignAppointmentToDoctor(@PathVariable Long doctorId, @RequestBody AppointmentDto appointmentDto) {
        return doctorService.assignAppointmentToDoctor(doctorId, appointmentDto);
    }

}
