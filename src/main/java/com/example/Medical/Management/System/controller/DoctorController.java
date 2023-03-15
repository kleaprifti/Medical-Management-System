package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.dto.AppointmentDto;
import com.example.Medical.Management.System.dto.DoctorDto;
import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.repository.DoctorRepository;
import com.example.Medical.Management.System.repository.PatientRepository;
import com.example.Medical.Management.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
