package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.dto.AppointmentDto;
import com.example.Medical.Management.System.service.AppointmentService;
import com.example.Medical.Management.System.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
  private final PatientService patientService;
    private final ModelMapper modelMapper;


//  private final DoctorService doctorService;

    public AppointmentController(AppointmentService appointmentService, PatientService patientService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.modelMapper =modelMapper;

    }
    @GetMapping
    public List<AppointmentDto> getAllAppointments() {
        return appointmentService.getAllAppointments();
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
