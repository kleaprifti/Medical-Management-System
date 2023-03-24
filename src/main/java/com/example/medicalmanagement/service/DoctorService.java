package com.example.medicalmanagement.service;

import com.example.medicalmanagement.dto.DoctorDto;
import com.example.medicalmanagement.model.Doctor;
import com.example.medicalmanagement.repository.DoctorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }


    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDto = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorDto doctorDto1 = modelMapper.map(doctor, DoctorDto.class);
            doctorDto.add(doctorDto1);
        }
        return doctorDto;
    }
}