package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class ApplicationTest {

    @Inject
    MyService myService;

    @Inject
    MeterRegistry meterRegistry;

    @Inject
    EmbeddedApplication<?> application;

    @Test
    public void testCountedMethod() {
        myService.countedMethod();
        var counterMeters = meterRegistry.getMeters().stream().filter(meter -> meter.getId().getName().equals("my_metric_counter")).collect(Collectors.toList());
        assertTrue(counterMeters.size() > 0);
    }

    @Test
    public void testTimedMethod() {
        myService.timedMethod();
        var timerMeters = meterRegistry.getMeters().stream().filter(meter -> meter.getId().getName().equals("my_metric_timer")).collect(Collectors.toList());
        assertTrue(timerMeters.size() > 0);
    }

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }
}