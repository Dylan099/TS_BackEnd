package com.example.demo.controller;


import com.example.demo.bl.PrediccionBl;
import com.example.demo.domain.ConsultaEntity;
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
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class PrediccionController {

    PrediccionBl prediccionBl;

    @Autowired
    public PrediccionController(PrediccionBl prediccionBl) {
        this.prediccionBl = prediccionBl;
    }


    @RequestMapping(value = "/sintomasPa/{idPaciente}", method = RequestMethod.POST)
    public ResponseEntity testP(@RequestBody ConsultaEntity consultaEntity, @PathVariable(value = "idPaciente")int idPaciente, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return null;
        }
        prediccionBl.answer(consultaEntity, idPaciente);

        return new ResponseEntity(new PrediccionController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/resultados/{idPaciente}")
    public List<ConsultaEntity> create_pacientes_list(@PathVariable(value = "idPaciente")int idPaciente) {

        List<ConsultaEntity> consultaEntities = prediccionBl.resultado_ultima_consulta(idPaciente);

        return consultaEntities;
    }

    @RequestMapping(value = "/testPDF/{idPaciente}")
    public ResponseEntity test_PDF (@PathVariable(value = "idPaciente")int idPaciente) throws DocumentException, IOException, URISyntaxException {
        ByteArrayInputStream bais =prediccionBl.create_pdf(idPaciente);;
        HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline: filename = prediccion.pdf");
            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));

    }





public class Mensaje {
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }


}
