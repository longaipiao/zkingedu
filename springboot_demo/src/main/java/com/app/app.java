package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class app {

    public static void main(String[] args) {
        SpringApplication.run(app.class, args);
    }


    @RequestMapping(value = "/")
    public String index() {
        return "springbootApp_8081  我是飘";
    }

}
