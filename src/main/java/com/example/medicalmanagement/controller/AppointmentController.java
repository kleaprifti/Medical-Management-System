package com.example.medicalmanagement.controller;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import com.example.medicalmanagement.repository.AppointmentRepository;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {


  private final AppointmentRepository appointmentRepository;

   @Autowired
  public AppointmentController(AppointmentRepository appointmentRepository) {

      this.appointmentRepository = appointmentRepository;
   }


   @GetMapping("")
   public Set<Appointment> getAppointmentsBetweenDatesAndTimes(@RequestParam Long doctorId, @RequestParam LocalDateTime startDateTime, @RequestParam LocalDateTime endDateTime) {
       Set<Appointment> appointments = new HashSet<>();
       LocalDateTime currentDateTime = startDateTime.truncatedTo(ChronoUnit.HOURS);

       while (currentDateTime.isBefore(endDateTime)) {
           LocalDateTime appointmentEndDateTime = currentDateTime.plusHours(1);

           List<Appointment> currentAppointments = appointmentRepository.findByDoctorIdAndAppointmentDateStartTimeBetween(doctorId, currentDateTime, appointmentEndDateTime);

           appointments.addAll(currentAppointments);

           currentDateTime = currentDateTime.plusHours(1);
       }

       return appointments;
   }




}
