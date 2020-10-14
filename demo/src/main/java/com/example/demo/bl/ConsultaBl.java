package com.example.demo.bl;

import com.example.demo.dao.ConsultaRepository;
import com.example.demo.domain.ConsultaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaBl {
    ConsultaRepository consultaRepository;

    @Autowired
    public ConsultaBl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public ConsultaEntity findById(int id){
        return consultaRepository.findConsultaEntityByIdConsulta(id);
    }
}
