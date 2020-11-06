package com.example.demo.controller;


import com.example.demo.bl.PacienteBl;
import com.example.demo.bl.RegisterBl;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.DoctorDto;
import com.example.demo.dto.PacienteDto;
import com.example.demo.dto.SuscripcionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class pacienteController {
    RegisterBl registerBl;
    PacienteBl pacienteBl;
    DoctorRepository doctorRepository;
    PacienteRepository pacienteRepository;

    @Autowired
    public pacienteController(RegisterBl registerBl,PacienteBl pacienteBl, PacienteRepository pacienteRepository, DoctorRepository doctorRepository) {
        this.registerBl = registerBl;
        this.pacienteBl = pacienteBl;
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }

    @RequestMapping(value = "/logindoctor", method = RequestMethod.POST)
    public ResponseEntity loginDoc(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.checkLogin(doctorRequest.getCorreo(),doctorRequest.getPass(),"doctor"))
            return new ResponseEntity(doctorRepository.findDoctorEntityByCorreo(doctorRequest.getCorreo()).getIdDoctor(), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity loginPac(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.checkLogin(pacienteRequest.getCorreo(),pacienteRequest.getPass(),"paciente"))
            return new ResponseEntity(pacienteRepository.findPacienteEntityByCorreo(pacienteRequest.getCorreo()).getIdPaciente(), HttpStatus.ACCEPTED);
        else
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public ResponseEntity subToDoc(@RequestBody SuscripcionDto suscripcionDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        int idPaciente = pacienteRepository.findPacienteEntityByCorreo(suscripcionDto.getCorreoPaciente()).getIdPaciente();
        int idDoctor = doctorRepository.findDoctorEntityByCorreo(suscripcionDto.getCorreoDoctor()).getIdDoctor();
        registerBl.subToDoc(idPaciente,idDoctor);
        return new ResponseEntity(new Mensaje("Se suscribio correctamente"),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/regisdoct", method = RequestMethod.POST)
    public ResponseEntity registerDoctor(@RequestBody DoctorEntity doctorRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.verificarCorreoExistenteDoctor(doctorRequest.getCorreo()))
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        registerBl.registrarDoctor(new DoctorDto(doctorRequest.getFirstName(),doctorRequest.getLastName(),doctorRequest.getCi(),
                doctorRequest.getCorreo(),doctorRequest.getUsername(),doctorRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity registerPaciente(@RequestBody PacienteEntity pacienteRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        }
        if(registerBl.verificarCorreoExistentePaciente(pacienteRequest.getCorreo()))
            return new ResponseEntity(new Mensaje("Error"), HttpStatus.BAD_REQUEST);
        registerBl.registrarPaciente(new PacienteDto(pacienteRequest.getFirstName(),pacienteRequest.getLastName(),pacienteRequest.getCi(),pacienteRequest.getSexo(),pacienteRequest.getEdad(),"1",pacienteRequest.getCorreo(),pacienteRequest.getUsername(),pacienteRequest.getPass()));
        return new ResponseEntity(new Mensaje("Creado"), HttpStatus.CREATED);
    }


    @GetMapping(value = "/editPatient/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteEntity edit_paciente_inicio(@PathVariable(value = "idPaciente")int idPaciente) {
        //Recupera los datos de los pacientes del doctor con el id ""
        PacienteEntity pacienteEntity= pacienteBl.recuperar_datos(idPaciente);
        return pacienteEntity;
    }

    @PutMapping(value = "/editPatient/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity edit_paciente(@RequestBody PacienteEntity pacienteEntity, @PathVariable(value = "idPaciente")int idPaciente, BindingResult bindingResult) {
        //Recupera los datos de los pacientes del doctor con el id ""
        pacienteBl.actualizar_datos(pacienteEntity);
        return new ResponseEntity(new pacienteController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/editPatient/deletePatient/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete_paciente(@RequestBody PacienteEntity pacienteEntity, @PathVariable(value = "idPaciente")int idPaciente, BindingResult bindingResult) {
        //Recupera los datos de los pacientes del doctor con el id ""
        System.out.println("delete>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        pacienteBl.delete_paciente(pacienteEntity);
        return new ResponseEntity(new pacienteController.Mensaje("Bien"), HttpStatus.ACCEPTED);
    }

    //Se inicia el proceso de recuperacion de contraseña
    @GetMapping(value = "/passRecoveryPac/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity passRecoveryStartPac(@PathVariable(value = "idPaciente")int idDoctor) throws TelegramApiException {
        return pacienteBl.checkChatIdPac(idDoctor)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    //Si NO existe el chatId se envia un codigo en la pagina para el bot
    @GetMapping(value = "/passRecoveryPac/setChatId/{idPaciente}")
    @ResponseStatus(HttpStatus.OK)
    public String passRecoverySetChatIdPac(@PathVariable(value = "idPaciente")int idDoctor){
        return pacienteBl.createCodeNewPassPac(idDoctor);
    }

    //Si existe el chatId se espera un codigo
    @GetMapping(value = "/passRecoveryPac/enterCode/{idPaciente}/{code}")
    @ResponseStatus(HttpStatus.OK)
    //TODO: Añadir response body con el codigo
    public ResponseEntity passRecoveryEnterCodePac(@PathVariable(value = "idPaciente")int idDoctor,@PathVariable(value = "code")String code) throws TelegramApiException {
        //Cambio al estado opuesto
        return pacienteBl.checkCodePac(idDoctor, code)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }

    //Si el codigo ingresado es correcto recibe la contraseña nueva y la guarda
    @GetMapping(value = "/passRecoveryPac/enterPass/{idPaciente}/{pass}")
    @ResponseStatus(HttpStatus.OK)
    //TODO: Añadir response body con el codigo
    public ResponseEntity passRecoverySetPassPac(@PathVariable(value = "idPaciente")int idDoctor,@PathVariable(value = "pass")String pass) throws TelegramApiException {
        return pacienteBl.setNewPassPac(idDoctor, pass)? new ResponseEntity(new DoctorController.Mensaje("Bien"), HttpStatus.ACCEPTED):new ResponseEntity(new DoctorController.Mensaje("Incorrecto"), HttpStatus.UNAUTHORIZED);
    }


    public static class Mensaje{
        public String mensaje;
        public Mensaje(String mensaje){
            this.mensaje = mensaje;
        }
    }
}

