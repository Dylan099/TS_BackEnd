package com.example.demo.controller;

import com.example.demo.bl.PacienteBl;
import com.example.demo.bl.PacienteConsultaBl;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SearchController {
    PacienteBl pacienteBl;
    PacienteConsultaBl pacienteConsultaBl;
    @Autowired
    public SearchController(PacienteBl pacienteBl, PacienteConsultaBl pacienteConsultaBl) {
        this.pacienteBl = pacienteBl;
        this.pacienteConsultaBl = pacienteConsultaBl;
    }

    @RequestMapping(value = "/searchBySex", method = RequestMethod.POST)
    public List<PacienteEntity> searchSex(@RequestBody DoctorController.Mensaje mensaje, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return null;
        }
        if(mensaje.mensaje.equals("Masculino")){
            return pacienteBl.findSex("masculino");
        }
        else if(mensaje.mensaje.equals("Femenino")){
            return pacienteBl.findSex("femenino");
        }
        else
            return null;
    }
    @RequestMapping(value = "/searchMale")
    public List<PacienteEntity> searchSex(){
        return pacienteBl.findSex("masculino");
    }
    @RequestMapping(value = "/searchFemale")
    public List<PacienteEntity> searchSexF(){
        return pacienteBl.findSex("femenino");
    }

    @RequestMapping(value = "/searchHealthy")
    public List<PacienteEntity> searchStatusHealthy(){
        return pacienteBl.findStatus(1);
    }
    @RequestMapping(value = "/searchInfected")
    public List<PacienteEntity> searchStatusInfected(){
        return pacienteBl.findStatus(2);
    }

    //TODO: Recibir edad por consulta o cambiar el tipo de dato para hacer busqueda por rango
    @RequestMapping(value = "/searchAge20")
    public List<PacienteEntity> searchAge(){
        return pacienteBl.findAge("20");
    }

    //TODO: Recibir el id de paciente por consulta
    @RequestMapping(value = "/searchTimeLine1")
    public List<String[]> searchTimeLine(){
        return pacienteConsultaBl.PacTimeLine(1);
    }
}
