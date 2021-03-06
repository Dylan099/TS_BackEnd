package com.example.demo.controller;

import com.example.demo.bl.PacienteBl;
import com.example.demo.bl.PacienteConsultaBl;
import com.example.demo.domain.ConsultaEntity;
import com.example.demo.domain.PacienteEntity;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

    @GetMapping(value = "/api/buscarMasculino/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchSexM(@PathVariable(value = "idDoctor")int idDoc){
        return pacienteBl.findSex(idDoc,"masculino");
    }

    @GetMapping(value = "/api/buscarFemenino/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchSexF(@PathVariable(value = "idDoctor")int idDoc){
        return pacienteBl.findSex(idDoc,"femenino");
    }

    @GetMapping(value = "/api/buscarSano/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchHealthy(@PathVariable(value = "idDoctor")int idDoc){
        return pacienteBl.findStatus(idDoc,1);
    }

    @GetMapping(value = "/api/buscarInfectado/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchInfected(@PathVariable(value = "idDoctor")int idDoc){
        return pacienteBl.findStatus(idDoc,2);
    }

    @GetMapping(value = "/api/buscarEdad/{idDoctor}/{edad}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchAge(@PathVariable(value = "idDoctor")int idDoc, @PathVariable(value = "edad")String idPac){
        return pacienteBl.findAge(idDoc,idPac);
    }

    @GetMapping(value = "/api/buscarNombre/{idDoctor}/{nombre}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchName(@PathVariable(value = "idDoctor")int idDoc,@PathVariable(value = "nombre")String idPac){
        return pacienteBl.findName(idDoc,idPac);
    }

    @GetMapping(value = "/api/buscarApellido/{idDoctor}/{apellido}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> searchSurname(@PathVariable(value = "idDoctor")int idDoc,@PathVariable(value = "apellido")String idPac){
        return pacienteBl.findApellido(idDoc,idPac);
    }

    //TODO: Recibir el id de paciente por consulta
    @GetMapping(value = "/api/searchTimeLine/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultaEntity> searchTimeLine(@PathVariable(value = "idPaciente")int id){
        List<ConsultaEntity> nreturn = pacienteConsultaBl.PacTimeLine(id);
        return nreturn;
    }

    @GetMapping(value = "/api/searchTimeLinePDF/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity PacTimeLinePDF(@PathVariable(value = "idPaciente")int id) throws IOException, DocumentException {

        ByteArrayInputStream bais =pacienteConsultaBl.pacTimeLinePDF(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline: filename = sintomas_paciente.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));

    }





    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }

}
