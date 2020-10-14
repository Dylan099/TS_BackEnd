package com.example.demo.dao;

import com.example.demo.domain.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity,Integer> {
    ConsultaEntity findConsultaEntityByIdConsulta(int id);
}
