package com.example.demo.dao;

import com.example.demo.domain.EstatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<EstatusEntity,Integer> {
    EstatusEntity findByIdStatus(int id);
}
