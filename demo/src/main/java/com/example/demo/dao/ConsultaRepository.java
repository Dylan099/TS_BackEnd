package com.example.demo.dao;

import com.example.demo.domain.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity,Integer> {
    ConsultaEntity findConsultaEntityByIdConsulta(int id);

    @Query(
            value = "SELECT COUNT(p.id_consulta),p.date_consulta FROM consulta p GROUP BY(p.date_consulta)",
            nativeQuery = true)
    List<String[]> findCountContagiadosFecha();

    @Query(
            value = "SELECT COUNT(p.breathing_problem),COUNT(p.fever),COUNT(p.dry_cough),COUNT(p.sore_throat),COUNT(p.running_se),COUNT(p.asthma),COUNT(p.chronic_lung_disease),COUNT(p.headache),COUNT(p.heart_disease),COUNT(p.diabetes),COUNT(p.hyper_tension),COUNT(p.fatigue),COUNT(p.gastrointestinal),COUNT(p.abroad_travel),COUNT(p.contact_with_covid_patient),COUNT(p.attended_large_gathering),COUNT(p.visited_public_exposed_places),COUNT(p.family_working_in_public_exposed_places),COUNT(p.wearing_masks),COUNT(p.sanitization_from_market) FROM consulta p WHERE p.covid=1",
            nativeQuery = true)
    List<String[]> findCountSintomas();
}
