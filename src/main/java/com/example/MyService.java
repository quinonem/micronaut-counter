package com.example;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.inject.Singleton;

@Singleton
public class MyService {

    @Timed("my_metric_timer")
    public String timedMethod() {
        return "hello";
    }

    @Counted("my_metric_counter")
    public String countedMethod() {
        return "hello";
    }
}
