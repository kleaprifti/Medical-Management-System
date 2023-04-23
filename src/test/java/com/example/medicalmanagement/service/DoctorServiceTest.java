//package com.example.medicalmanagement.service;
//
//import com.example.medicalmanagement.dto.DoctorDto;
//import com.example.medicalmanagement.model.Doctor;
//import com.example.medicalmanagement.repository.DoctorRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class DoctorServiceTest {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceTest.class);
//
//    @Mock
//    private DoctorRepository doctorRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private DoctorService doctorService;
//
//    @Test
//    public void testGetAllDoctors() {
//        LOGGER.info("Testing getAllDoctors method...");
//
//        Doctor doctor1 = new Doctor();
//        doctor1.setId(1L);
//        doctor1.setFullName("John Smith");
//        doctor1.setProfile("Pediatrician");
//
//        Doctor doctor2 = new Doctor();
//        doctor2.setId(2L);
//        doctor2.setFullName("Jane Smith");
//        doctor2.setProfile("General");
//
//        List<Doctor> doctors = Arrays.asList(doctor1, doctor2);
//
//        DoctorDto doctorDto1 = new DoctorDto();
//        doctorDto1.setId(1L);
//        doctorDto1.setFullName("John Smith");
//        doctorDto1.setProfile("Pediatrician");
//
//        DoctorDto doctorDto2 = new DoctorDto();
//        doctorDto2.setId(2L);
//        doctorDto2.setFullName("Jane Smith");
//        doctorDto2.setProfile("General");
//
//        List<DoctorDto> expectedDoctorDtos = Arrays.asList(doctorDto1, doctorDto2);
//
//        when(doctorRepository.findAll()).thenReturn(doctors);
//        when(modelMapper.map(doctor1, DoctorDto.class)).thenReturn(doctorDto1);
//        when(modelMapper.map(doctor2, DoctorDto.class)).thenReturn(doctorDto2);
//
//        List<DoctorDto> actualDoctorDtos = doctorService.getAllDoctors();
//
//        assertEquals(expectedDoctorDtos, actualDoctorDtos);
//
//        LOGGER.info("The list of doctors is tested successfully ...");
//
// }
//    }