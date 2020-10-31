package com.example.demo.bl;

import com.example.demo.dao.PacienteRepository;
import com.example.demo.dao.RecomendacionRepository;
import com.example.demo.domain.PacienteEntity;
import com.example.demo.domain.RecomendacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecomendacionBl {

    private RecomendacionRepository recomendacionRepository;
    private PacienteRepository pacienteRepository;

    @Autowired
    public RecomendacionBl(RecomendacionRepository recomendacionRepository, PacienteRepository pacienteRepository) {
        this.recomendacionRepository = recomendacionRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void add_recomendacion(String recomendacionRequest, int idPaciente) {
        RecomendacionEntity recomendacionEntity= new RecomendacionEntity();
        recomendacionEntity.setRecomendacion(recomendacionRequest);
        recomendacionEntity.setIdPaciente(idPaciente);
        recomendacionRepository.save(recomendacionEntity);
    }


    public List<RecomendacionEntity> findhrecomendacion(int idPaciente) {
        List<RecomendacionEntity> recomendacionDtoList= new ArrayList<>();
        recomendacionDtoList = recomendacionRepository.findAllByIdPaciente(idPaciente);
        return recomendacionDtoList;
    }

    public int findnewrecomendacion(int idPaciente) {
        RecomendacionEntity recomendacionEntity = new RecomendacionEntity();
        int last_num = pacienteRepository.findCountNumRecomendacion(idPaciente);
        int new_num = recomendacionRepository.findCountNumRecomendacion(idPaciente);
        int num_rec= 0;
        if(new_num > last_num)
        {
            PacienteEntity pacienteEntity = pacienteRepository.findByIdPaciente(idPaciente);
            pacienteEntity.setNumRecomendacion(new_num);
            pacienteRepository.save(pacienteEntity);
            num_rec = new_num-last_num;
        }
        return num_rec;
    }

}
