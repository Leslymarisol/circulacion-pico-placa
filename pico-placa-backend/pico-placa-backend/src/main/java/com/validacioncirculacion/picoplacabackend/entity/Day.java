package com.validacioncirculacion.picoplacabackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="day")
public class Day {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDay;
    @Column(name = "weekday", length = 30, nullable = false)
    private String weekday;


    public Day(){

    }

    


    public Long getIdDay() {
        return idDay;
    }


    public void setIdDay(Long idDay) {
        this.idDay = idDay;
    }


    public String getWeekday() {
        return weekday;
    }


    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    
}
