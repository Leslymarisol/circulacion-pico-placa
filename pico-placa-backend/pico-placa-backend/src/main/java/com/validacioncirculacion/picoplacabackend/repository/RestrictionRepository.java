package com.validacioncirculacion.picoplacabackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.validacioncirculacion.picoplacabackend.entity.Restriction;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Long> {
  List<Restriction> findByLastDigitContaining(int lastDigit);

  @Query(value = ("SELECT r, d FROM Restriction r INNER JOIN r.day d  WHERE r.lastDigit LIKE %:filtro%"))
  List<Restriction> search(@Param("filtro") Integer filtro);


}
