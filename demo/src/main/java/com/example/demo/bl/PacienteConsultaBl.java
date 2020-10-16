package com.example.demo.bl;

import com.example.demo.dao.PacienteConsultaRepository;
import com.example.demo.domain.ConsultaEntity;
import com.example.demo.domain.PacienteConsultaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
