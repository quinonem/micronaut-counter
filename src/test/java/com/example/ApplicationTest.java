package com.example;

import io.micrometer.core.instrument.MeterRegistry;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class ApplicationTest {

    @Inject
    MeterRegistry meterRegistry;

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    public void testMeters() {
        final String result = client.toBlocking().retrieve(HttpRequest.GET("/app"), String.class);
        var counterMeters = meterRegistry.getMeters().stream().filter(meter -> meter.getId().getName().equals("my_metric_counter")).collect(Collectors.toList());
        var timerMeters = meterRegistry.getMeters().stream().filter(meter -> meter.getId().getName().equals("my_metric_timer")).collect(Collectors.toList());

        assertEquals("hello", result);
        assertTrue(timerMeters.size() > 0);
        assertTrue(counterMeters.size() > 0);
    }

    @Test
    void testItWorks() {
        Assertions.assertTrue(application.isRunning());
    }
}