package org.sjtu.backend.controllers;


//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class BackendController {
//    String home() {
//        "Hello World!"
//    }
//}

import org.springframework.boot.*;
        import org.springframework.boot.autoconfigure.*;
        import org.springframework.web.bind.annotation.*;

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
