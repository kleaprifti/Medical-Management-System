package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.repository.AppointmentRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentItemReader implements ItemReader<AppointmentDto> {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentItemReader(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public AppointmentDto read() throws Exception {

        try {
            AppointmentDto appointment = new AppointmentDto() ;

            return appointment;
        } catch (Exception e) {
            throw new Exception("Error reading appointment: " + e.getMessage());
        }
    }

}
