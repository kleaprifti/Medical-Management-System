package com.example.medicalmanagement.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentJobRunner {

    private final JobLauncher jobLauncher;
    private final Job processAppointmentsJob;

    @Autowired
    public AppointmentJobRunner(JobLauncher jobLauncher, Job processAppointmentsJob) {
        this.jobLauncher = jobLauncher;
        this.processAppointmentsJob = processAppointmentsJob;
    }

    public void runAppointmentJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(processAppointmentsJob, jobParameters);

            if (jobExecution.getStatus().isUnsuccessful()) {
                System.out.println("Job failed with status: " + jobExecution.getStatus());
            } else {
                System.out.println("Job completed successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
