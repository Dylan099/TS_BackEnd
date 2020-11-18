package com.example.demo.controller;


import com.example.demo.bl.RecomendacionBl;
import com.example.demo.dao.HospitalRepository;
import com.example.demo.domain.HospitalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class HospitalController {

    HospitalRepository hospitalRepository;

    @Autowired
    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping(value = "/hospitales")
    public ResponseEntity hospitales() {
        //Recupera los datos de los pacientes del doctor con el id ""
        List<HospitalEntity> ListHospital= hospitalRepository.findAll();
        return new ResponseEntity(ListHospital, HttpStatus.ACCEPTED);
    }

}
