package com.example.Medical.Management.System.service;

import com.example.Medical.Management.System.model.Appointment;
import com.example.Medical.Management.System.model.Doctor;
import com.example.Medical.Management.System.model.Patient;
import com.example.Medical.Management.System.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

        private final AppointmentRepository appointmentRepository;

        @Autowired
        public AppointmentService(AppointmentRepository appointmentRepository) {
            this.appointmentRepository = appointmentRepository;
        }

        public List<Appointment> getAppointmentsByPatient(Patient patient) {
            return appointmentRepository.findByPatient(patient);
        }

        public List<Appointment> getAppointmentsBetweenDates(Date start, Date end) {
            return appointmentRepository.findByAppointmentDateBetween(start, end);
        }

        public Appointment saveAppointment(Appointment appointment) {
            return appointmentRepository.save(appointment);
        }

        public void deleteAppointment(Long id) {
            appointmentRepository.deleteById(id);
        }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

//    public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
//        return appointmentRepository.findByDoctor(doctor);
//    }
}


