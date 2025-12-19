package org.example.carsharing.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//???
@RestController
public class HealthController {
    //proverka rabotatat li prilozheniya server
    @GetMapping("/api/health")
    public String health(){
        return "ok";
    }
}
