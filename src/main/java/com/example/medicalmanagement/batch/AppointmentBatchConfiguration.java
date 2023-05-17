package com.example.medicalmanagement.batch;

import com.example.medicalmanagement.dto.AppointmentDto;
import com.example.medicalmanagement.service.AppointmentService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class AppointmentBatchConfiguration {
    @Deprecated(since = "5.0.0", forRemoval = true)
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentBatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, AppointmentService appointmentService) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.appointmentService = appointmentService;
    }

    @Bean
    public ItemReader<AppointmentDto> appointmentReader() {

        return appointmentReader();
    }

    @Bean
    public ItemProcessor<AppointmentDto, AppointmentDto> appointmentProcessor() {

        return appointmentProcessor();
    }

    @Bean
    public ItemWriter<AppointmentDto> appointmentWriter() {

        return appointmentWriter();
    }

    @Bean
    public Step appointmentStep(ItemReader<AppointmentDto> reader, ItemProcessor<AppointmentDto, AppointmentDto> processor, ItemWriter<AppointmentDto> writer) {
        return stepBuilderFactory.get("appointmentStep")
                .<AppointmentDto, AppointmentDto>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job appointmentJob(Step appointmentStep) {
        return jobBuilderFactory.get("appointmentJob")
                .flow(appointmentStep)
                .end()
                .build();
    }
}
