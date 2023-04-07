package com.example;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.inject.Singleton;

@Singleton
public class MyService {

    @Timed("my_metric_timer")
    @Counted("my_metric_counter")
    public String hello() {
        return "hello";
    }
}
