package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.dao.ConsultaRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.dto.PacienteDto;
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
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class DoctorController {
    DoctorBl doctorBl;
    PacienteRepository pacienteRepository;
    ConsultaRepository consultaRepository;

    @Autowired
    public DoctorController(DoctorBl doctorBl, PacienteRepository pacienteRepository, ConsultaRepository consultaRepository) {
        this.doctorBl = doctorBl;
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
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
    public List<PacienteDto> create_pacientes_list(@PathVariable(value = "idDoctor")int idDoc) {
        //Recupera los datos de los pacientes del doctor con el id ""
        List<PacienteDto> pacienteDtoList= doctorBl.create_pacientes_list_dto(idDoc);
        return pacienteDtoList;
    }

    //Grafica de Contagiados (Valor:SANOS)
    @GetMapping(value = "/graficaContagiados_Valor1/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int graficaContagiadosValor1 (@PathVariable(value = "idDoctor")int idDoc){
        return doctorBl.graficaContagiadosValor1(idDoc);
    }
    //Grafica de Contagiados (Valor: Contagiados)
    @GetMapping(value = "/graficaContagiados_Valor2/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int graficaContagiadosValor2 (@PathVariable(value = "idDoctor")int idDoc){
        return doctorBl.graficaContagiadosValor2(idDoc);
    }

    //Grafica de Contagiados por Sexo (VALOR: MASCULINOS)
    @GetMapping(value = "/graficaContagiadosSexoMasculino/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int graficaContagiadosSexoMasculino (@PathVariable(value = "idDoctor")int idDoc){
        return doctorBl.graficaContagiadosSexoMasculino(idDoc);
    }

    //Grafica de Contagiados por Sexo (VALOR: Femeninos)
    @GetMapping(value = "/graficaContagiadosSexoFemenino/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int graficaContagiadosSexoFemenino (@PathVariable(value = "idDoctor")int idDoc){
        return doctorBl.graficaContagiadosSexoFemenino(idDoc);
    }

    //Grafica de Contagiados por Edad(Valor: Contagiados)
    @GetMapping(value = "/graficaContagiadosEdadValor1/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int[] graficaContagiadosEdadValor1 (@PathVariable(value = "idDoctor")int idDoc){
        return pacienteRepository.findCountPacienteEdades(idDoc,2);
    }
    //Grafica de Contagiados por Edad(Valor: Edades)
    @GetMapping(value = "/graficaContagiadosEdadValor2/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public int[] graficaContagiadosEdadValor2 (@PathVariable(value = "idDoctor")int idDoc){
        return pacienteRepository.findCountPacienteEdad(idDoc,2);
    }

    //Grafica de Contagiados por Edad(Valor: Fechas)
    @GetMapping(value = "/graficaContagiadosFechaValor1/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public String[] graficaContagiadosFechaValor1(@PathVariable(value = "idDoctor")int idDoc){
        return consultaRepository.findCountContagiadosFechaValor1();
    }
    //Grafica de Contagiados por Edad(Valor: Contagiados)
    @GetMapping(value = "/graficaContagiadosFechaValor2/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public String[] graficaContagiadosFechaValor2(@PathVariable(value = "idDoctor")int idDoc){
        return consultaRepository.findCountContagiadosFechaValor2();
    }

    @GetMapping(value = "/graficaSintomas/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public List<String[]> graficaSintomas(@PathVariable(value = "idDoctor")int idDoc){
        return consultaRepository.findCountSintomas();
    }


    @GetMapping(value = "/listpatientPDF/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity create_pdf_pacientes_list(@PathVariable(value = "idDoctor")int idDoc) throws IOException, DocumentException {
        //Recupera los datos de los pacientes del doctor con el id "" y los guarda en un pdf
        ByteArrayInputStream bais = doctorBl.create_pdf_pacientes_list(idDoc);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline: filename = lista.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));

    }



    @GetMapping(value = "/editDoctor/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public DoctorEntity edit_doctor_inicio(@PathVariable(value = "idDoctor")int idDoctor) {
        //Recupera los datos de los pacientes del doctor con el id ""
        DoctorEntity doctorEntity= doctorBl.recuperar_datos(idDoctor);
        return doctorEntity;
    }

    @PutMapping(value = "/editDoctor/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity edit_doctor(@RequestBody DoctorEntity doctorEntity, BindingResult bindingResult) {
        //Recupera los datos de los pacientes del doctor con el id ""
        doctorBl.actualizar_datos(doctorEntity);
        return new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/editDoctor/deleteDoctor/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete_paciente(@RequestBody DoctorEntity doctorEntity, @PathVariable(value = "idDoctor")int idDoctor, BindingResult bindingResult) {
        //Recupera los datos de los pacientes del doctor con el id ""
        doctorBl.delete_paciente(doctorEntity);
        return new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }


}
