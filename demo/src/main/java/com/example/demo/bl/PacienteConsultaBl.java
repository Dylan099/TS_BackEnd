package com.example.demo.bl;

import com.example.demo.dao.PacienteConsultaRepository;
import com.example.demo.domain.ConsultaEntity;
import com.example.demo.domain.PacienteConsultaEntity;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PacienteConsultaBl {
    PacienteConsultaRepository pacienteConsultaRepository;
    PacienteBl pacienteBl;
    ConsultaBl consultaBl;

    @Autowired
    public PacienteConsultaBl(PacienteConsultaRepository pacienteConsultaRepository, PacienteBl pacienteBl, ConsultaBl consultaBl) {
        this.pacienteConsultaRepository = pacienteConsultaRepository;
        this.pacienteBl = pacienteBl;
        this.consultaBl = consultaBl;
    }

    public List<ConsultaEntity> PacTimeLine(int paciente){
        List<PacienteConsultaEntity> pacienteConsultaEntities = pacienteConsultaRepository.findAllByIdPaciente(paciente);
        if(pacienteConsultaEntities.isEmpty()){
            System.out.println("vacio");
            return null;
        }
        System.out.println(" no vacio");
        List<ConsultaEntity> strings = new ArrayList<>();
        for (PacienteConsultaEntity entity: pacienteConsultaEntities
             ) {
            ConsultaEntity consultaEntity= consultaBl.findById(entity.getIdConsulta());
            strings.add(consultaEntity);
        }
        return strings;
    }

    public void pacTimeLinePDF(int idPaciente) throws IOException, DocumentException {
        List<ConsultaEntity> consultaEntityList = PacTimeLine(idPaciente);

        Document document = new Document(PageSize.LETTER, 80, 80, 50, 75);
        PdfWriter.getInstance(document, new FileOutputStream("paciente.pdf"));

        document.open();

        Image logoP = Image.getInstance ("images/logoP.png");
        logoP.scaleToFit(500, 400);
        logoP.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(logoP);
        Image linea = Image.getInstance("images/linea.png");
        document.add(linea);

        Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
        Paragraph titulo = new Paragraph("Lista de sintomas del paciente \n\n", font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        if(consultaEntityList!=null)
        {
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(106);
            table.setHorizontalAlignment(1000);
            addTableHeader(table);
            addRows(table,consultaEntityList);
            document.add(table);
        }
        else
        {
            font.setSize(15);
            Paragraph nota = new Paragraph("El paciente no tiene registros \n\n", font);
            nota.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(nota);
        }
        document.close();

    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("dateConsulta",
                "Problema de respiración",
                "fiebre",
                "dryCough",
                "soreThroat",
                "Problemas respiratorios",
                "asma",
                "ChronicLungDisease",
                "dolor de cabeza",
                "heartDisease",
                "diabetes",
                "hipertensión",
                "fatiga",
                "gastrointestinal",
                "viajes al extranjero",
                "contac",
                "wentLargeGathering",
                "VisitPublicExposedPlaces",
                "familyWorkingInPublicExposedPlaces",
                "vistiendoMáscaras",
                "sanitizationFromMarket",
                "covid")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, List<ConsultaEntity> consultaEntityList) {

        for (ConsultaEntity p:consultaEntityList) {
            table.addCell(p.getDateConsulta());
            table.addCell(p.getBreathingProblem());
            table.addCell(p.getFever());
            table.addCell(p.getDryCough());
            table.addCell(p.getSoreThroat());
            table.addCell(p.getRunningSe());
            table.addCell(p.getAsthma());
            table.addCell(p.getChronicLungDisease());
            table.addCell(p.getHeadache());
            table.addCell(p.getHeartDisease());
            table.addCell(p.getDiabetes());
            table.addCell(p.getHyperTension());
            table.addCell(p.getFatigue());
            table.addCell(p.getGastrointestinal());
            table.addCell(p.getAbroadTravel());
            table.addCell(p.getContactWithCovidPatient());
            table.addCell(p.getAttendedLargeGathering());
            table.addCell(p.getVisitedPublicExposedPlaces());
            table.addCell(p.getFamilyWorkingInPublicExposedPlaces());
            table.addCell(p.getWearingMasks());
            table.addCell(p.getSanitizationFromMarket());
            table.addCell(p.getCovid());
        }
    }



}
