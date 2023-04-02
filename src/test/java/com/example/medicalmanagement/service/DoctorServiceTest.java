package com.example.medicalmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.example.medicalmanagement.dto.DoctorDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.medicalmanagement.model.Doctor;
import com.example.medicalmanagement.repository.DoctorRepository;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DoctorService doctorService;

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceTest.class);

    @Test
    public void testGetAllDoctors() {
        logger.info("Testing getAllDoctors method");

        // Create some test data
        Doctor doctor1 = new Doctor(1L, "John Doe", "profile1", null);
        Doctor doctor2 = new Doctor(2L, "Jane Smith", "profile2", null);
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);

        DoctorDto doctorDto1 = new DoctorDto(1L, "John Doe", "profile1");
        DoctorDto doctorDto2 = new DoctorDto(2L, "Jane Smith", "profile2");
        List<DoctorDto> doctorDtos = new ArrayList<>();
        doctorDtos.add(doctorDto1);
        doctorDtos.add(doctorDto2);

        // Mock the repository's findAll() method to return the test data
        when(doctorRepository.findAll()).thenReturn(doctors);

        // Mock the modelMapper's map() method to map the test data to the DTOs
        when(modelMapper.map(doctor1, DoctorDto.class)).thenReturn(doctorDto1);
        when(modelMapper.map(doctor2, DoctorDto.class)).thenReturn(doctorDto2);

        // Call the method being tested
        List<DoctorDto> result = doctorService.getAllDoctors();

        // Verify that the repository's findAll() method was called exactly once
        verify(doctorRepository, times(1)).findAll();

        // Verify that the modelMapper's map() method was called twice with the correct arguments
        verify(modelMapper, times(1)).map(doctor1, DoctorDto.class);
        verify(modelMapper, times(1)).map(doctor2, DoctorDto.class);

        // Verify that the method returned the expected result
        assertEquals(doctorDtos, result);

        logger.info("getAllDoctors method test completed successfully");
    }

}
