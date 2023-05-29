package com.validacioncirculacion.picoplacabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.validacioncirculacion.picoplacabackend.entity.CirculationHours;

@Repository
public interface CirculationHoursRepository extends JpaRepository<CirculationHours, Long> {
 
}
