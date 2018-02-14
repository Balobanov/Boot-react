package com.balobanov.scheduling;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * TODO: enable Schedule
 */
//@Component
public class Schedule {

    private JobLauncher jobLauncher;

    private Job job;

    private int a = 0;

    @Scheduled(fixedDelay = 10000L)
    public void reportCurrentTime() {
        if(a == 0){
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
            a++;
        }
    }

    @Autowired
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Autowired
    @Qualifier(value = "remoteDepartmentJobBean")
    public void setJob(Job job) {
        this.job = job;
    }
}
