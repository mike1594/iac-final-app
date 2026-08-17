package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hola, soy Joe Michelle Meza Cardama";
    }

    @GetMapping("/secreto")
    public String secreto(@Value("${APP_SECRET:sin-configurar}") String secreto) {
        return "El secreto es: " + secreto;
    }
}