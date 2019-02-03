package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BrowserMain {

    public static void main(String[] args) {
        SpringApplication.run(BrowserMain.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security!";
    }

}
