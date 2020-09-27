package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.domain.PacienteEntity;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class DoctorController {
    DoctorBl doctorBl;

    @Autowired
    public DoctorController(DoctorBl doctorBl) {
        this.doctorBl = doctorBl;
    }

    @RequestMapping("/listaPacientes")
    public List<PacienteEntity> create_pacientes_list() {
        //Recupera los datos de los pacientes del doctor con el id ""
        List<PacienteEntity> pacienteDtoList= doctorBl.create_pacientes_list(1);
        return pacienteDtoList;

    }


    @RequestMapping("/listaPacientesPDF")
    public String create_pdf_pacientes_list() throws FileNotFoundException, DocumentException {
        //Recupera los datos de los pacientes del doctor con el id "" y los guarda en un pdf
        doctorBl.create_pdf_pacientes_list(1);
        return "Creado";

    }

}