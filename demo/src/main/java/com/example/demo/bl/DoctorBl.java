package com.example.demo.bl;


import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.PacienteDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class DoctorBl {
    private PacienteRepository pacienteRepository;
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorBl(PacienteRepository pacienteRepository, DoctorRepository doctorRepository) {

        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
    }


    public List<PacienteEntity> pacienteDtoList(int idDoctor){
        List<PacienteEntity> pacienteEntityList = pacienteRepository.findAllByIdDoctor(idDoctor);
        return pacienteEntityList;
    }

    public List<PacienteEntity> create_pacientes_list(int id_doctor) {
        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);
        return pacienteEntityList;

    }


    public List<String> pacienteNameList(int id_doctor){
        List<String> pacienteNameList = pacienteRepository.findFirstNameByIdDoctor(id_doctor);
        return pacienteNameList;
    }

    public List<String> create_pacientes_list_name(int id_doctor) {
        List<String> pacienteNameList= pacienteNameList(id_doctor);
        return pacienteNameList;

    }


    public void create_pdf_pacientes_list(int id_doctor) throws FileNotFoundException, DocumentException {

        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("pacienteList.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(5);
        addTableHeader(table);
        addRows(table,pacienteEntityList);

        document.add(table);
        document.close();

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
            table.addCell(p.getLastStatus());
            table.addCell(p.getCorreo());
        }
    }
}
