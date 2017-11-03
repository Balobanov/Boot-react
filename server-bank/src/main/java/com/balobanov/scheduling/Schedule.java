package com.balobanov.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Component
public class Schedule {

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        CompletableFuture.supplyAsync(() -> {
            System.out.println(LocalDateTime.now());
            return null;
        });
    }
}
