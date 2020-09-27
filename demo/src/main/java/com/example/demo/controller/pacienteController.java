package com.example.demo.controller;


import com.example.demo.bl.RegisterBl;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class pacienteController {
    RegisterBl registerBl;

    @Autowired
    public pacienteController(RegisterBl registerBl) {
        this.registerBl = registerBl;
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

    @RequestMapping("/waso")
    public String registerDoctor(){
        //TODO: Recuper formulario de registro
        registerBl.registrarDoctor(new DoctorDto("Cris","Castro","5992709",
                "castro.inofuentes.cristopher@gmail.com","CLCastro","password"));
        return "Guardado Doctor";
    }

    @RequestMapping("/maswaso")
    public String registerPaciente(){
        //TODO: Recuper formulario de registro
        registerBl.registrarPaciente(new PacienteDto("Cris","Castro","5992709",
                "castro.inofuentes.cristopher@gmail.com","Sano",0,"CLCAstro","password"));
        return "Guardado Paciente";
    }

    @RequestMapping("/menoswaso")
    public String menoswaso(){
        return registerBl.getFullName();
    }
}

