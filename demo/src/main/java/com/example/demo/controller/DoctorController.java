package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.domain.PacienteEntity;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class DoctorController {
    DoctorBl doctorBl;

    @Autowired
    public DoctorController(DoctorBl doctorBl) {
        this.doctorBl = doctorBl;
    }


    @GetMapping(value = "/listaPacientesNombre/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> create_pacientes_list_name(@PathVariable(value = "idDoctor")int idDoc) {
        //Recupera los datos de los pacientes del doctor con el id ""
        List<String> pacientesListName= doctorBl.create_pacientes_list_name(idDoc);
        return pacientesListName;
    }

    @GetMapping(value = "/listpatient/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<PacienteEntity> create_pacientes_list(@PathVariable(value = "idDoctor")int idDoc) {
        //Recupera los datos de los pacientes del doctor con el id ""
        List<PacienteEntity> pacienteDtoList= doctorBl.create_pacientes_list(idDoc);
        return pacienteDtoList;
    }

    @GetMapping(value = "/listpatientPDF/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity create_pdf_pacientes_list(@PathVariable(value = "idDoctor")int idDoc) throws IOException, DocumentException {
        //Recupera los datos de los pacientes del doctor con el id "" y los guarda en un pdf
        doctorBl.create_pdf_pacientes_list(idDoc);
        return new ResponseEntity(new DoctorController.Mensaje("Creado"), HttpStatus.ACCEPTED);

    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }
}