package com.dreamgames.backendengineeringcasestudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// It is implemented to check server status. Don't modify this class.

@RestController
public class StatusController {

    @GetMapping("/status")
    public String status() {
        return "Server is up!";
    }
}
