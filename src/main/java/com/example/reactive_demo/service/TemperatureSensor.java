package com.example.reactive_demo.service;

import com.example.reactive_demo.domain.Temperature;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TemperatureSensor {
    private final ApplicationEventPublisher publisher;
    private final Random rnd = new Random();
    private final ScheduledExecutorService executorService =
            Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void startProcessing(){
        this.executorService.schedule(this::prove, 1, TimeUnit.SECONDS);
    }

    private void prove(){
        double temperature = rnd.nextGaussian()*10;
        publisher.publishEvent(new Temperature(temperature));
        executorService.schedule(this::prove, rnd.nextInt(5000), TimeUnit.MILLISECONDS);
    }
}
