package com.example.demo.bl;



import com.example.demo.dao.DoctorRepository;
import com.example.demo.dao.PacienteRepository;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.PacienteDto;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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


    public void create_pdf_pacientes_list(int id_doctor) throws IOException, DocumentException {

        List<PacienteEntity> pacienteEntityList= pacienteDtoList(id_doctor);

        Document document = new Document(PageSize.LETTER, 80, 80, 50, 75);
        PdfWriter.getInstance(document, new FileOutputStream("pacienteList.pdf"));

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

    public List<Integer[]> graficaContagiados (int id_doctor ){
        int pacientesSanos  = pacienteRepository.findCountPacienteStatus(id_doctor,1);
        int pacientesEnfermos  = pacienteRepository.findCountPacienteStatus(id_doctor,2);

        List<Integer[]> valoresGrafica = new ArrayList<>();
        valoresGrafica.add(new Integer[] {pacientesSanos,pacientesEnfermos});

        return valoresGrafica;
    }

    public List<Integer[]> graficaContagiadosSexo (int id_doctor){
        int pacientesMasculino  = pacienteRepository.findCountPacienteSexo(id_doctor,"masculino",2);
        int pacientesFemenino = pacienteRepository.findCountPacienteSexo(id_doctor,"femenino",2);

        List<Integer[]> valoresGrafica = new ArrayList<>();
        valoresGrafica.add(new Integer[] {pacientesMasculino,pacientesFemenino});

        return valoresGrafica;
    }
}
