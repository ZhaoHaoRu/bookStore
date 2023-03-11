package org.sjtu.backend.controllers;


//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class BackendController {
//    String home() {
//        "Hello World!"
//    }
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class BackendController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendController.class, args);
    }

}
