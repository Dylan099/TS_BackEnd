package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    public class pacienteController {
        @RequestMapping("/waso")
        public String waso(){return "waso";}
    }

