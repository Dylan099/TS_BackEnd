package com.example.demo.controller;


import com.example.demo.bl.DoctorBl;
import com.example.demo.bl.RegisterBl;
import com.example.demo.bot.BotInit;
import com.example.demo.dao.ConsultaRepository;
import com.example.demo.dao.DoctorRepository;
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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class DoctorController {
    DoctorBl doctorBl;
    RegisterBl registerBl;
    DoctorRepository doctorRepository;
    PacienteRepository pacienteRepository;
    ConsultaRepository consultaRepository;

    @Autowired
    public DoctorController(DoctorBl doctorBl,RegisterBl registerBl, DoctorRepository doctorRepository, PacienteRepository pacienteRepository, ConsultaRepository consultaRepository) {
        this.doctorBl = doctorBl;
        this.registerBl = registerBl;
        this.doctorRepository = doctorRepository;
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



    @PostMapping(value = "/reactivationDoctor")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity reactivationDoctor(@RequestBody DoctorEntity doctorEntity, BindingResult bindingResult) {
        //Recupera los datos de los pacientes del doctor con el id ""
        boolean ret = doctorBl.reactivationDoctor(doctorEntity);
        return new ResponseEntity(ret, HttpStatus.ACCEPTED);
    }

    //Se solicita activar dobleAuth
    //TODO: DEFINIR URL
    @GetMapping(value = "/Account/doubleAuth/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public String change_doubleAuth(@PathVariable(value = "idDoctor")int idDoctor) {
        return doctorBl.change_doubleAuth(idDoctor);
    }


    @RequestMapping(value = "/logindoctor", method = RequestMethod.POST)
    public ResponseEntity loginDoc(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new pacienteController.Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.checkLogin(doctorRequest.getCorreo(),doctorRequest.getPass(),"doctor"))
            return new ResponseEntity(doctorRepository.findDoctorEntityByCorreo(doctorRequest.getCorreo()).getIdDoctor(), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity(new pacienteController.Mensaje("Error"), HttpStatus.BAD_REQUEST);
    }

    //Segundo login con dobleAuth
    @GetMapping(value = "/loginDoc/doubleAuth/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity doubleAuth(@PathVariable(value = "idDoctor")int idDoctor) throws TelegramApiException {
        //Cambio al estado opuesto
        return doctorBl.doubleAuth(idDoctor)? new ResponseEntity(true, HttpStatus.ACCEPTED):new ResponseEntity( false, HttpStatus.OK);
    }

    //El doctor ingresa su codigo recibido en telegram
    @GetMapping(value = "/loginDoc/doubleAuth/{idDoctor}/{code}")
    @ResponseStatus(HttpStatus.OK)
    //TODO: Añadir response body con el codigo
    public ResponseEntity checkCodeDoc(@PathVariable(value = "idDoctor")int idDoctor,@PathVariable(value = "code")String code) throws TelegramApiException {
        //Cambio al estado opuesto
        return doctorBl.checkCode(idDoctor, code)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    //Se inicia el proceso de recuperacion de contraseña
    @GetMapping(value = "/passRecovery/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity passRecoveryStart(@PathVariable(value = "idDoctor")int idDoctor) throws TelegramApiException {
        return doctorBl.checkChatId(idDoctor)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    //Si NO existe el chatId se envia un codigo en la pagina para el bot
    @GetMapping(value = "/passRecovery/setChatId/{idDoctor}")
    @ResponseStatus(HttpStatus.OK)
    public String passRecoverySetChatId(@PathVariable(value = "idDoctor")int idDoctor){
        return doctorBl.createCodeNewPass(idDoctor);
    }

    //Si existe el chatId se espera un codigo
    @GetMapping(value = "/passRecovery/enterCode/{idDoctor}/{code}")
    @ResponseStatus(HttpStatus.OK)
    //TODO: Añadir response body con el codigo
    public ResponseEntity passRecoveryEnterCode(@PathVariable(value = "idDoctor")int idDoctor,@PathVariable(value = "code")String code) throws TelegramApiException {
        //Cambio al estado opuesto
        return doctorBl.checkCode(idDoctor, code)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    //Si el codigo ingresado es correcto recibe la contraseña nueva y la guarda
    @GetMapping(value = "/passRecovery/enterPass/{idDoctor}/{pass}")
    @ResponseStatus(HttpStatus.OK)
    //TODO: Añadir response body con el codigo
    public ResponseEntity passRecoverySetPass(@PathVariable(value = "idDoctor")int idDoctor,@PathVariable(value = "pass")String pass) throws TelegramApiException {
        return doctorBl.setNewPass(idDoctor, pass)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }



}
