package com.example.demo.bl;

import com.example.demo.domain.PacienteEntity;
import com.example.demo.dto.SintomasDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class PrediccionBl {

    static final String HOST = "localhost";
    static final int PUERTO = 500;

    public PrediccionBl() {
    }

    public String answer(SintomasDto sintomasDto) {
        Socket socket = null;
        String answer ="";
        try {
            socket = new Socket(HOST,PUERTO);
            send_data(socket, sintomasDto);
            answer= recive_data(socket);
            socket.close();

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
        String R =tokens.nextToken();


        String answer = "Con un "+ R + "% Usted "+sino+ " tiene Covid-19";
        return answer;
    }


    public void create_pdf(String answer) throws FileNotFoundException, DocumentException {

        Document document = new Document(PageSize.LETTER, 80, 80, 75, 75);
        PdfWriter.getInstance(document, new FileOutputStream("testPDF.pdf"));
        document.open();

        Paragraph titulo = new Paragraph();
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        titulo.setFont(FontFactory.getFont("Times New Roman", 36, Font.BOLD, BaseColor.BLACK));
        titulo.add("Resultado de: Covid-19");
        document.add(titulo);

        Paragraph text = new Paragraph();
        text.setAlignment(Paragraph.ALIGN_CENTER);
        text.setFont(FontFactory.getFont("Times New Roman", 18, Font.NORMAL, BaseColor.BLACK));
        text.setAlignment(Element.ALIGN_JUSTIFIED);
        text.add(" \n"+answer+" \n\nRecuerde que esta es una preddicion solamente, para un resultado mas certero consulte a su medico ");
        document.add(text);


        document.close();

    }
}
