package com.balobanov.scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Schedule {

    private JobLauncher jobLauncher;

    private Job job;

    @Scheduled(fixedDelay = 10000000000000L)
    public void reportCurrentTime() {
//        CompletableFuture.supplyAsync(() -> {
//            JobParameters jobParameters =
//                    new JobParametersBuilder()
//                            .addString("name", RandomStringUtils.random(15))
//                            .toJobParameters();
//            try {
//                this.jobLauncher.run(job, jobParameters);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        });
    }

    @Autowired
    public void setJobLauncher(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    @Autowired
    @Qualifier(value = "banksFtpCsvJob")
    public void setJob(Job job) {
        this.job = job;
    }
}
