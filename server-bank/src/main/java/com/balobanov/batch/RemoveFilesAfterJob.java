package com.balobanov.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class RemoveFilesAfterJob {

    public void clean(Message<JobExecution> message) throws IOException {
        String pathToFile = message.getPayload().getJobParameters().getString("pathToFile");
        Files.delete(Paths.get(pathToFile));
    }
}
