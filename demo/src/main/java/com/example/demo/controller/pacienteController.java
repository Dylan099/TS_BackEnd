package com.example.demo.controller;


import com.example.demo.bl.RegisterBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.AccessControlException;

@RestController
public class pacienteController {
    RegisterBl registerBl;
    DoctorRepository doctorRepository;
    PacienteRepository pacienteRepository;

    @Autowired
    public pacienteController(RegisterBl registerBl,PacienteRepository pacienteRepository, DoctorRepository doctorRepository) {
        this.registerBl = registerBl;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping("/loginDoc")
    public String loginDoc(){
        //TODO: Recuper datos de login
        if(registerBl.checkLogin("ClCastro","password","doctor"))
            return "Acceso doctor";
        else
            return "Denegado Doctor";
    }

    @RequestMapping("/loginPac")
    public String loginPac(){
        //TODO: Recuper datos de login
        if(registerBl.checkLogin("Nose","password","paciente"))
            return "Acceso paciente";
        else
            return "Denegado Paciente";
    }

    @RequestMapping("/subToDoc")
    public String subToDoc(){
        //TODO: Recuperar campos del form de suscripcion a doctor
        registerBl.subToDoc(1,1);
        return "Suscrito a doctor";
    }

    @RequestMapping("/waso")
    public String registerDoctor(){
        //TODO: Recuper formulario de registro
        registerBl.registrarDoctor(new DoctorDto("Cris","Castro","5992709",
                "castro.inofuentes.cristopher@gmail.com","CLCastro","password"));
        return "Guardado Doctor";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerPaciente(){
        //TODO: Recuper formulario de registro

        registerBl.registrarPaciente(new PacienteDto("Cris","Castro","5992709",
                "castro.inofuentes.cristopher@gmail.com","Sano",0,"CLCAstro","password"));
        System.out.println("REGISTRADO DE PACIENTE CON FRONT");
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }

    @RequestMapping("/menoswaso")
    public String menoswaso(){
        return registerBl.getFullName();
    }
}

