package com.cms.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class WelcomeController {

    @GetMapping("/ping")
    public String ping(){
        System.out.println("Fdfd");

        return "ping";
    }

    @GetMapping("/ping-pong")
    public String pingPong(){
        return "ping pong";
    }
}
