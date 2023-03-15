package com.example.Medical.Management.System.controller;

import com.example.Medical.Management.System.dto.PatientDto;
import com.example.Medical.Management.System.repository.PatientRepository;
import com.example.Medical.Management.System.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final ModelMapper modelMapper;
    private PatientRepository patientRepository;
    private PatientService patientService;

    @Autowired
    public PatientController(PatientRepository patientRepository, PatientService patientService, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") Long id) {
        Optional<PatientDto> patientDtoOptional = patientService.getPatientById(id);
        if (patientDtoOptional.isPresent()) {
            return ResponseEntity.ok(patientDtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PatientDto> addPatient(@RequestBody PatientDto patientDto) {
        PatientDto patient = patientService.addPatient(patientDto);
        PatientDto patientResponse = modelMapper.map(patient, PatientDto.class);
        return new ResponseEntity<PatientDto>(patientResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    }



