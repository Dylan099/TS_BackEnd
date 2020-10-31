package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.bl.RecomendacionBl;
import com.example.demo.dao.ConsultaRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.RecomendacionEntity;
import com.example.demo.dto.PacienteDto;
import com.example.demo.dto.RecomendacionDto;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class RecomendacionController {
    RecomendacionBl recomendacionBl;

    @Autowired
    public RecomendacionController(RecomendacionBl recomendacionBl) {
        this.recomendacionBl = recomendacionBl;
    }


    @RequestMapping(value = "/addrecomendacion/{idPaciente}/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addrecomendacion(@RequestBody String recomendacionRequest, @PathVariable(value = "idPaciente")int idPaciente) {
        //Recupera los datos de los pacientes del doctor con el id ""
        recomendacionBl.add_recomendacion(recomendacionRequest , idPaciente);
        return new ResponseEntity(new RecomendacionController.Mensaje("Enviado"), HttpStatus.OK);
    }

    @GetMapping(value = "/findhrecomendacion/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findhrecomendacion(@PathVariable(value = "idPaciente")int idPaciente) {
        List<RecomendacionEntity> recomendacionDtoList= new ArrayList<>();
        recomendacionDtoList = recomendacionBl.findhrecomendacion(idPaciente);
        return new ResponseEntity( recomendacionDtoList , HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/findnewrecomendacion/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findnewrecomendacion(@PathVariable(value = "idPaciente")int idPaciente) {
        RecomendacionEntity recomendacion;
        int num_rec = recomendacionBl.findnewrecomendacion(idPaciente);
        return new ResponseEntity( num_rec , HttpStatus.ACCEPTED);
    }



    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }


}
