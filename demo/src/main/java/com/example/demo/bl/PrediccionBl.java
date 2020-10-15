package com.example.demo.bl;

import com.example.demo.dao.ConsultaRepository;
import com.example.demo.domain.ConsultaEntity;
import com.example.demo.dto.SintomasDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import com.itextpdf.text.Image;

@Service
public class PrediccionBl {

    static final String HOST = "localhost";
    static final int PUERTO = 500;

    private ConsultaRepository consultaRepository;

    @Autowired
    public PrediccionBl( ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public String answer(ConsultaEntity consultaEntity) {
        Socket socket = null;
        String answer ="";
        SintomasDto sintomasDto = convertir_sintomaDto(consultaEntity);

        try {
            socket = new Socket(HOST,PUERTO);
            send_data(socket, sintomasDto);
            answer= recive_data(socket);
            socket.close();

            String[] datos = answer.split("Usted ");
            datos = datos[1].split("tiene ");
            consultaEntity.setCovid(datos[0]);
            consultaRepository.save(consultaEntity);
            // FALTA GURDAR RESULTADO

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public static void send_data(Socket socket, SintomasDto sintomasDto) throws IOException {
        DataOutputStream mensajeSalida = new DataOutputStream(socket.getOutputStream());
        System.out.println("sintomasDto.getBreathingProblem() "+sintomasDto.getBreathingProblem());

        mensajeSalida.write(sintomasDto.getBreathingProblem());
        mensajeSalida.write(sintomasDto.getFever());
        mensajeSalida.write(sintomasDto.getDryCough());
        mensajeSalida.write(sintomasDto.getSoreThroat());
        mensajeSalida.write(sintomasDto.getRunningNose());
        mensajeSalida.write(sintomasDto.getAsthma());
        mensajeSalida.write(sintomasDto.getChronicLungDisease());
        mensajeSalida.write(sintomasDto.getHeadache());
        mensajeSalida.write(sintomasDto.getHeartDisease());
        mensajeSalida.write(sintomasDto.getDiabetes());
        mensajeSalida.write(sintomasDto.getHyperTension());
        mensajeSalida.write(sintomasDto.getFatigue());
        mensajeSalida.write(sintomasDto.getGastrointestinal());
        mensajeSalida.write(sintomasDto.getAbroadTravel());
        mensajeSalida.write(sintomasDto.getContactWithCovidPatient());
        mensajeSalida.write(sintomasDto.getAttendedLargeGathering());
        mensajeSalida.write(sintomasDto.getVisitedPublicExposedPlaces());
        mensajeSalida.write(sintomasDto.getFamilyWorkingInPublicExposedPlaces());
        mensajeSalida.write(sintomasDto.getWearingMasks());
        mensajeSalida.write(sintomasDto.getSanitizationFromMarket());



    }


    public String recive_data(Socket socket) throws IOException {
        System.out.println("recive_data ");

        BufferedReader stdIn =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mensajeEntrada = stdIn.readLine();

        StringTokenizer tokens=new StringTokenizer(mensajeEntrada);
        String sino=tokens.nextToken();
        String R = String.valueOf((Float.parseFloat(tokens.nextToken())*100));


        String answer = "Con un "+ R + "% Usted "+sino+ " tiene Covid-19";
        return answer;
    }


    public void create_pdf(String answer) throws IOException, DocumentException, URISyntaxException {

        Document document = new Document(PageSize.LETTER, 80, 80, 50, 75);
        PdfWriter.getInstance(document, new FileOutputStream("testPDF.pdf"));
        document.open();


        Image logoP = Image.getInstance ("images/logoP.png");
        logoP.scaleToFit(500, 400);
        logoP.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(logoP);
        Image linea = Image.getInstance ("images/linea.png");
        document.add(linea);


        BaseFont baseFont = BaseFont.createFont("images/Madina.ttf", BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);

        font.setSize(36);
        Paragraph titulo = new Paragraph("Resultados", font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titulo);

        font.setSize(20);
        Paragraph text = new Paragraph(answer, font);
        text.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(text);

        document.close();

    }

    public SintomasDto convertir_sintomaDto(ConsultaEntity consultaEntity) {

        int dateConsulta = convertir_string_int(consultaEntity.getDateConsulta());
        int breathingProblem = convertir_string_int(consultaEntity.getBreathingProblem());
        int fever = convertir_string_int(consultaEntity.getFever());
        int dryCough = convertir_string_int(consultaEntity.getDryCough());
        int soreThroat = convertir_string_int(consultaEntity.getSoreThroat());
        int runningSe = convertir_string_int(consultaEntity.getRunningSe());
        int asthma = convertir_string_int(consultaEntity.getAsthma());
        int chronicLungDisease = convertir_string_int(consultaEntity.getChronicLungDisease());
        int headache = convertir_string_int(consultaEntity.getHeadache());
        int heartDisease = convertir_string_int(consultaEntity.getHeartDisease());
        int diabetes = convertir_string_int(consultaEntity.getDiabetes());
        int hyperTension = convertir_string_int(consultaEntity.getHyperTension());
        int fatigue = convertir_string_int(consultaEntity.getFatigue());
        int gastrointestinal = convertir_string_int(consultaEntity.getGastrointestinal());
        int abroadTravel = convertir_string_int(consultaEntity.getAbroadTravel());
        int contactWithCovidPatient = convertir_string_int(consultaEntity.getContactWithCovidPatient());
        int attendedLargeGathering = convertir_string_int(consultaEntity.getAttendedLargeGathering());
        int visitedPublicExposedPlaces = convertir_string_int(consultaEntity.getVisitedPublicExposedPlaces());
        int familyWorkingInPublicExposedPlaces = convertir_string_int(consultaEntity.getFamilyWorkingInPublicExposedPlaces());
        int wearingMasks = convertir_string_int(consultaEntity.getWearingMasks());
        int sanitizationFromMarket = convertir_string_int(consultaEntity.getSanitizationFromMarket());

        SintomasDto sintomasDto =new SintomasDto(breathingProblem,fever,dryCough,soreThroat,runningSe,asthma,chronicLungDisease,headache,heartDisease,diabetes,hyperTension,fatigue,gastrointestinal,abroadTravel,contactWithCovidPatient,attendedLargeGathering,visitedPublicExposedPlaces,familyWorkingInPublicExposedPlaces,wearingMasks, sanitizationFromMarket);

        return sintomasDto;

    }


    public int convertir_string_int (String dato){
        int ret = 0;
        if (dato==null)
            return ret;
        if(dato.equals("no"))
            ret =0;
        else
            ret = 1;
        return ret;
    }
}
