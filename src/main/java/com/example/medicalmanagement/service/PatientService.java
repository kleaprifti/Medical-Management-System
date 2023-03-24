package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.PatientDto;
import com.example.medicalmanagement.model.Patient;
import com.example.medicalmanagement.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;

    }

    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDTOS = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
            patientDTOS.add(patientDto);
        }
        return patientDTOS;
    }



    public Optional<PatientDto> getPatientById(Long id) {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        return patientOptional.map(patient -> modelMapper.map(patient, PatientDto.class));
    }



    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.delete(patient);
    }






}
