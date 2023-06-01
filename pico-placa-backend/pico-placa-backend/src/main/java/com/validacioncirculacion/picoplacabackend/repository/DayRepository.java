package com.validacioncirculacion.picoplacabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.validacioncirculacion.picoplacabackend.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long>{
    
   
}
