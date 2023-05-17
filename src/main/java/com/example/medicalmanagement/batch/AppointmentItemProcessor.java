package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.model.Appointment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class AppointmentItemProcessor implements ItemProcessor<AppointmentDto, Appointment> {

    @Override
    public Appointment process(AppointmentDto appointmentDto) throws Exception {
        return new Appointment();
    }
}
