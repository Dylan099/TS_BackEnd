package com.example.demo.controller;


import com.example.demo.bl.PrediccionBl;
import com.example.demo.dto.SintomasDto;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @RequestMapping("/testPDF")
    public void create_pdf_pacientes_list() throws IOException, DocumentException {
        //Llama a prediccionBl para el test----sintomasDto creado temporalmente para pruebas
        SintomasDto sintomasDto = new SintomasDto(1,1,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0);
        String answer= prediccionBl.answer(sintomasDto);
        prediccionBl.create_pdf(answer);

    }

}
