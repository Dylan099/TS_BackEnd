package com.example.demo.dao;

import com.example.demo.domain.RecomendacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecomendacionRepository extends JpaRepository<RecomendacionEntity,Integer> {

    List<RecomendacionEntity> findAllByIdPaciente(int idPaciente);


    @Query(
            value = "SELECT COUNT(*) FROM recomendacion p WHERE p.id_paciente = ? ",
            nativeQuery = true)
    int findCountNumRecomendacion(int idPaciente);

    @Query(
            value = "SELECT * FROM recomendacion p WHERE p.id_paciente = ? ORDER BY id_recomendacion DESC LIMIT 1 ",
            nativeQuery = true)
    RecomendacionEntity findLastRecomByIdPaciente(int idPaciente);

}
