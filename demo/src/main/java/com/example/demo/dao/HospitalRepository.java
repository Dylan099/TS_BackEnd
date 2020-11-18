package com.example.demo.dao;

import com.example.demo.domain.ConsultaEntity;
import com.example.demo.domain.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity,Integer> {

    List<HospitalEntity> findAll();

}
