package com.example.demo.controller;


import com.example.demo.bl.PrediccionBl;
import com.example.demo.dto.SintomasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrediccionController {

    PrediccionBl prediccionBl;

    @Autowired
    public PrediccionController(PrediccionBl prediccionBl) {
        this.prediccionBl = prediccionBl;
    }


    @RequestMapping("/test")
    public String test() {
        //Llama a prediccionBl para el test----sintomasDto creado temporalmente para pruebas
        SintomasDto sintomasDto = new SintomasDto(1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0);
        String answer= prediccionBl.answer(sintomasDto);
        return answer;
    }

    /*
    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity subToDoc(@RequestBody SuscripcionDto suscripcionDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        int idPaciente = pacienteRepository.findPacienteEntityByCorreo(suscripcionDto.getCorreoPaciente()).getIdPaciente();
        int idDoctor = doctorRepository.findDoctorEntityByCorreo(suscripcionDto.getCorreoDoctor()).getIdDoctor();
        registerBl.subToDoc(idPaciente,idDoctor);
        return new ResponseEntity(new Mensaje("Se suscribio correctamente"),HttpStatus.ACCEPTED);
    }
     */
}
