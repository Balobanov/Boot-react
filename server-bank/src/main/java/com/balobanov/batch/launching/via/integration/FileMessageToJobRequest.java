package com.balobanov.batch.launching.via.integration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileMessageToJobRequest {
    private Job job;

    public void setJob(Job job) {
        this.job = job;
    }

    public JobLaunchRequest toRequest(Message<File> message) {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addString("pathToFile", message.getPayload().getPath());
        jobParametersBuilder.addLong("run.id", System.currentTimeMillis()).toJobParameters();
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        return new JobLaunchRequest(job, jobParameters);
    }
}