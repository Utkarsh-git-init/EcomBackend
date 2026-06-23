package com.utkarsh.ecombackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String greet(){
        return "Welcome to Ecom";
    }

    @GetMapping("/healthcheck")
    public String healthCheck(){
        return "We are up";
    }
}
