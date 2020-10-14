package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.domain.PacienteEntity;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @RequestMapping("/listaPacientesNombre")
    public List<String> create_pacientes_list_name() {
        List<String> pacientesListName= doctorBl.create_pacientes_list_name(2);
        return pacientesListName;

    }

    @RequestMapping("/listpatient")
    public List<PacienteEntity> create_pacientes_list() {
        List<PacienteEntity> pacienteDtoList= doctorBl.create_pacientes_list(2);
        return pacienteDtoList;

    }

    @RequestMapping("/listpatientPDF")
    public ResponseEntity create_pdf_pacientes_list() throws IOException, DocumentException {
        //Recupera los datos de los pacientes del doctor con el id "" y los guarda en un pdf
        doctorBl.create_pdf_pacientes_list(2);
        return new ResponseEntity(new DoctorController.Mensaje("Creado"), HttpStatus.ACCEPTED);

    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }
}