package com.example.demo.dao;

import com.example.demo.domain.DoctorEntity;
import com.example.demo.domain.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<EstatusEntity,Integer> {
    EstatusEntity findByIdStatus(int id);
}
