package com.example.demo.bl;

import com.example.demo.dto.SintomasDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

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
        System.out.println("mensajeEntrada "+mensajeEntrada);

        return mensajeEntrada;
    }


}
