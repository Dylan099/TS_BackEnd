package com.example.demo.controller;


import com.example.demo.bl.PrediccionBl;
import com.example.demo.domain.ConsultaEntity;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class PrediccionController {

    PrediccionBl prediccionBl;

    @Autowired
    public PrediccionController(PrediccionBl prediccionBl) {
        this.prediccionBl = prediccionBl;
    }


    @RequestMapping(value = "/sintomasPa", method = RequestMethod.POST)
    public ResponseEntity test(@RequestBody ConsultaEntity consultaEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new PrediccionController.Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        String answer= prediccionBl.answer(consultaEntity);
        System.out.println("Answer ..............."+answer);
        return new ResponseEntity(new PrediccionController.Mensaje(answer), HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/testPDF", method = RequestMethod.POST)
    public ResponseEntity test_PDF(@RequestBody ConsultaEntity consultaEntity, BindingResult bindingResult) throws DocumentException, IOException, URISyntaxException {
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new PrediccionController.Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        //SintomasDto sintomasDto = new SintomasDto(1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0);
        String answer= prediccionBl.answer(consultaEntity);
        prediccionBl.create_pdf(answer);
        return new ResponseEntity(new PrediccionController.Mensaje(answer), HttpStatus.ACCEPTED);
    }


    public class Mensaje {
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }

}
