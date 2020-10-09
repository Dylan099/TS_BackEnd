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
    static final int PUERTO = 50;

    public PrediccionBl() {
    }

    public String answer(SintomasDto sintomasDto) {
        Socket socket = null;
        String answer ="";
        try {
            socket = new Socket(HOST,PUERTO);
            send_data(socket, sintomasDto);
            answer= recive_data(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void send_data(Socket socket, SintomasDto sintomasDto) throws IOException {
        String getBreathingProblem=String.valueOf(sintomasDto.getBreathingProblem());
        String getFever=String.valueOf(sintomasDto.getFever());
        String getDryCough=String.valueOf(sintomasDto.getDryCough());
        String getSoreThroat=String.valueOf(sintomasDto.getSoreThroat());
        String getRunningNose=String.valueOf(sintomasDto.getRunningNose());
        String getAsthma=String.valueOf(sintomasDto.getAsthma());
        String getChronicLungDisease=String.valueOf(sintomasDto.getChronicLungDisease());
        String getHeadache=String.valueOf(sintomasDto.getHeadache());
        String getHeartDisease=String.valueOf(sintomasDto.getHeartDisease());
        String getDiabetes=String.valueOf(sintomasDto.getDiabetes());
        String getHyperTension=String.valueOf(sintomasDto.getHyperTension());
        String getFatigue=String.valueOf(sintomasDto.getFatigue());
        String getGastrointestinal=String.valueOf(sintomasDto.getGastrointestinal());
        String getAbroadTravel=String.valueOf(sintomasDto.getAbroadTravel());
        String getContactWithCovidPatient=String.valueOf(sintomasDto.getContactWithCovidPatient());
        String getAttendedLargeGathering=String.valueOf(sintomasDto.getAttendedLargeGathering());
        String getVisitedPublicExposedPlaces=String.valueOf(sintomasDto.getVisitedPublicExposedPlaces());
        String getFamilyWorkingInPublicExposedPlaces=String.valueOf(sintomasDto.getFamilyWorkingInPublicExposedPlaces());
        String getWearingMasks=String.valueOf(sintomasDto.getWearingMasks());
        String getSanitizationFromMarket=String.valueOf(sintomasDto.getSanitizationFromMarket());

        DataOutputStream mensajeSalida = new DataOutputStream(socket.getOutputStream());

        mensajeSalida.writeUTF(getBreathingProblem);
        mensajeSalida.writeUTF(getFever);
        mensajeSalida.writeUTF(getDryCough);
        mensajeSalida.writeUTF(getSoreThroat);
        mensajeSalida.writeUTF(getRunningNose);
        mensajeSalida.writeUTF(getAsthma);
        mensajeSalida.writeUTF(getChronicLungDisease);
        mensajeSalida.writeUTF(getHeadache);
        mensajeSalida.writeUTF(getHeartDisease);
        mensajeSalida.writeUTF(getDiabetes);
        mensajeSalida.writeUTF(getHyperTension);
        mensajeSalida.writeUTF(getFatigue);
        mensajeSalida.writeUTF(getGastrointestinal);
        mensajeSalida.writeUTF(getAbroadTravel);
        mensajeSalida.writeUTF(getContactWithCovidPatient);
        mensajeSalida.writeUTF(getAttendedLargeGathering);
        mensajeSalida.writeUTF(getVisitedPublicExposedPlaces);
        mensajeSalida.writeUTF(getFamilyWorkingInPublicExposedPlaces);
        mensajeSalida.writeUTF(getWearingMasks);
        mensajeSalida.writeUTF(getSanitizationFromMarket);
    }

    public String recive_data(Socket socket) throws IOException {
        BufferedReader stdIn =new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mensajeEntrada = stdIn.readLine();
        return mensajeEntrada;
    }


}
