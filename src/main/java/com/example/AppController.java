package com.example;


import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("app")
public class AppController {

    MyService myService;

    public AppController(MyService myService) {
        this.myService = myService;
    }

    @Get
    public String endpoint() {
        return myService.hello();
    }
}
