package com.example.demo.bl;



import com.example.demo.bot.BotInit;
import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.dao.StatusRepository;
import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.Estatus;
import com.example.demo.dto.PacienteDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class DoctorBl {
    private PacienteRepository pacienteRepository;
    private StatusRepository statusRepository;
    private DoctorRepository doctorRepository;
    private BotInit botBl;

    @Autowired
    public DoctorBl(PacienteRepository pacienteRepository, StatusRepository statusRepository, DoctorRepository doctorRepository, BotInit botBl) {
        this.pacienteRepository = pacienteRepository;
        this.statusRepository = statusRepository;
        this.doctorRepository = doctorRepository;
        this.botBl = botBl;
    }


    public List<PacienteEntity> pacienteDtoList(int idDoctor){
        List<PacienteEntity> pacienteEntityList = pacienteRepository.findAllByIdDoctorAndEstatus(idDoctor, Estatus.ACTIVE.getStatus());
        return pacienteEntityList;
    }

    public List<PacienteEntity> create_pacientes_list(int id_doctor) {
        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);
        return pacienteEntityList;

    }

    public List<PacienteDto> create_pacientes_list_dto(int id_doctor) {
        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);
        List<PacienteDto> pacienteDtoList =convertr_entity_dto(pacienteEntityList);
        return pacienteDtoList;

    }


    private List<PacienteDto> convertr_entity_dto(List<PacienteEntity> pacienteEntityList) {
        List<PacienteDto> pacienteDtoList = new ArrayList<>();
        for (PacienteEntity pacienteEntity: pacienteEntityList) {
            String status = statusRepository.findByIdStatus(pacienteEntity.getIdStatus()).getEstatus();
            PacienteDto pacienteDto = new PacienteDto(pacienteEntity.getIdPaciente(), pacienteEntity.getFirstName(), pacienteEntity.getLastName(),pacienteEntity.getCi(),pacienteEntity.getSexo(),pacienteEntity.getEdad(),status,pacienteEntity.getCorreo(),pacienteEntity.getUsername(), pacienteEntity.getPass(), pacienteEntity.getIdDoctor());
            pacienteDtoList.add(pacienteDto);
        }
        return  pacienteDtoList;
    }




        public List<String> pacienteNameList(int id_doctor){
        List<String> pacienteNameList = pacienteRepository.findFirstNameByIdDoctor(id_doctor);
        return pacienteNameList;
    }

    public List<String> create_pacientes_list_name(int id_doctor) {
        List<String> pacienteNameList= pacienteNameList(id_doctor);
        return pacienteNameList;

    }


    public ByteArrayInputStream create_pdf_pacientes_list(int id_doctor) throws IOException, DocumentException {

        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);

        Document document = new Document(PageSize.LETTER, 80, 80, 50, 75);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        Image logoP = Image.getInstance ("images/logoP.png");
        logoP.scaleToFit(500, 400);
        logoP.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(logoP);
        Image linea = Image.getInstance("images/linea.png");
        document.add(linea);

        Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
        Paragraph titulo = new Paragraph("Lista completa de pacientes \n\n", font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(106);
        table.setHorizontalAlignment(1000);
        addTableHeader(table);
        addRows(table,pacienteEntityList);

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());

    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Nombre", "Apellido", "CI", "Estado","Correo")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<PacienteEntity> pacienteEntityList) {

        for (PacienteEntity p:pacienteEntityList) {
            table.addCell(p.getFirstName());
            table.addCell(p.getLastName());
            table.addCell(p.getCi());
            table.addCell(p.getIdStatus().toString());
            table.addCell(p.getCorreo());
        }
    }

    public int graficaContagiadosValor1 (int id_doctor ){
        int pacientesSanos  = pacienteRepository.findCountPacienteStatus(id_doctor,1);
        return pacientesSanos;
    }

    public int graficaContagiadosValor2 (int id_doctor ){
        int pacientesEnfermos  = pacienteRepository.findCountPacienteStatus(id_doctor,2);
        return pacientesEnfermos;
    }


    public int graficaContagiadosSexoMasculino (int id_doctor){
        int pacientesMasculino  = pacienteRepository.findCountPacienteSexo(id_doctor,"masculino",2);
        return pacientesMasculino;
    }

    public int graficaContagiadosSexoFemenino (int id_doctor){
        int pacientesFemenino = pacienteRepository.findCountPacienteSexo(id_doctor,"femenino",2);
        return pacientesFemenino;
    }


    public DoctorEntity recuperar_datos(int idDoctor) {
        return doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
    }

    public void actualizar_datos(DoctorEntity doctorEntity) {
        doctorRepository.save(doctorEntity);
    }


    public void delete_paciente(DoctorEntity doctorEntity) {
        doctorEntity.setEstatus(Estatus.INACTIVE.getStatus());
        doctorRepository.save(doctorEntity);
    }

    public String change_doubleAuth(int idDoctor){
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        String code = generateCode(idDoctor);
        if(doctorEntity.getDobleAuth()!=1){
            doctorEntity.setLastCod(code);
            doctorRepository.save(doctorEntity);
        }else{
            doctorEntity.setDobleAuth(0);
            doctorRepository.save(doctorEntity);
        }
        return code;
    }

    public boolean doubleAuth(int idDoctor) throws TelegramApiException {
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        System.out.println("DocID: "+doctorEntity.getIdDoctor()+" Chat ID -> "+doctorEntity.getTelNum());
        if(doctorEntity.getLastCod()!=null && doctorEntity.getDobleAuth()==1){
            botBl.sendCode(doctorEntity);
        }else{
            System.out.println("Codigo nulo o no tiene activa la doble autorizacion");
            return false;
        }
        return true;
    }

    public boolean checkCode(int idDoctor,String code) throws TelegramApiException {
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        if(doctorEntity.getLastCod().equals(code)){
            return true;
        }else{
            System.out.println("Codigo nulo o no tiene activa la doble autorizacion");
            return false;
        }
    }

    public boolean checkChatId(int idDoctor) throws TelegramApiException {
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        System.out.println("ChatID: "+ doctorEntity.getTelNum());
        System.out.println(doctorEntity.getTelNum() != null && !doctorEntity.getTelNum().isEmpty());
        if(doctorEntity.getTelNum() != null && !doctorEntity.getTelNum().isEmpty()){
            botBl.sendCodePass(doctorEntity);
            return true;
        }
        return false;
    }

    public String createCodeNewPass(int idDoctor){
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        String code = generateCodePass(idDoctor);
        doctorEntity.setLastCod(code);
        doctorRepository.save(doctorEntity);
        return code;
    }

    public boolean setNewPass(int idDoctor, String pass){
        DoctorEntity doctorEntity = doctorRepository.findDoctorEntityByIdDoctor(idDoctor);
        if(doctorEntity!=null && !pass.isEmpty()){
            doctorEntity.setPass(pass);
            doctorRepository.save(doctorEntity);
            return true;
        }
        return false;
    }

    private String generateCode(int id){
        String code = String.valueOf(id)+""+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }
    private String generateCodePass(int id){
        String code = String.valueOf(id)+"-"+(String.valueOf(Math.random() * (9999 - 999)) + 999);
        System.out.println("Generated Code: ->"+code);
        return code;
    }
}
