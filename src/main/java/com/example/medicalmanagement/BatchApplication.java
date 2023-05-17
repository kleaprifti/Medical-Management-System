package com.example.medicalmanagement;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }
    private final JobLauncher jobLauncher;
    private final Job appointmentJob;

    @Autowired
    public BatchApplication(JobLauncher jobLauncher, Job appointmentJob) {
        this.jobLauncher = jobLauncher;
        this.appointmentJob = appointmentJob;
    }



    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(appointmentJob, jobParameters);
    }
}
