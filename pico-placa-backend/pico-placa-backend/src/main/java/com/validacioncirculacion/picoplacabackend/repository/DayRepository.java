package com.validacioncirculacion.picoplacabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.validacioncirculacion.picoplacabackend.entity.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long>{
    List<Day> findByWeekdayContaining(String weekday);
 

    @Query(value=("SELECT d FROM Day d WHERE d.idDay LIKE %:filtro%"))
    List<Day> search(@Param("filtro") Integer filtro);

    @Query(
        value = "SELECT * FROM day WHERE day.weekday LIKE %:filtro%",
        nativeQuery = true
    )
    List<Day> searchNative(@Param("filtro") String filtro);
   
}
