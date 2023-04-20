package com.example.medicalmanagement.service;

import com.example.medicalmanagement.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        List<PatientDto> patientDto = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDto patientDto1 = modelMapper.map(patient, PatientDto.class);
            patientDto.add(patientDto1);
        }
        return patientDto;

    }

}