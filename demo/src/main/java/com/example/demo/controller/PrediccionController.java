package com.example.demo.controller;


import com.example.demo.bl.PrediccionBl;
import com.example.demo.domain.ConsultaEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.AnswerDto;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/sintomasPa", method = RequestMethod.POST)
    public ResponseEntity testP(@RequestBody ConsultaEntity consultaEntity, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return null;
        }
        prediccionBl.answer(consultaEntity);

        return new ResponseEntity(new PrediccionController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/resultados/{idPaciente}")
    public List<ConsultaEntity> create_pacientes_list(@PathVariable(value = "idPaciente")int idPaciente) {

        List<ConsultaEntity> consultaEntities = prediccionBl.resultado_ultima_consulta(idPaciente);
        System.out.println("......................"+idPaciente);

        return consultaEntities;
    }

    @RequestMapping(value = "/testPDF", method = RequestMethod.POST)
    public ResponseEntity test_PDF(@RequestBody ConsultaEntity consultaEntity, BindingResult bindingResult) throws DocumentException, IOException, URISyntaxException {
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new PrediccionController.Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        //SintomasDto sintomasDto = new SintomasDto(1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0);

        //AnswerDto answer= prediccionBl.answer(consultaEntity);
        //prediccionBl.create_pdf(answer,consultaEntity );
        return new ResponseEntity(new PrediccionController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }


    public class Mensaje {
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }


}
