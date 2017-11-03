package com.balobanov.scheduling;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class Schedule {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value = "banksToConsole")
    private Job job;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
        CompletableFuture.supplyAsync(() -> {
            JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addString("name", RandomStringUtils.random(15))
                            .toJobParameters();
            try {
                this.jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
