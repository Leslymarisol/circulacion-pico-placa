package com.validacioncirculacion.picoplacabackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "restriction")
public class Restriction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestriction;

    @Column(name = "lastDigit", nullable = false)
    private int lastDigit;

    @ManyToOne
    @JoinColumn(name = "idDay")
    private Day day;

    @ManyToOne
    @JoinColumn(name = "idHours")
    private CirculationHours hours;


    public Restriction(){
        
    }


    public Restriction( int lastDigit, Day day, CirculationHours hours) {
        this.lastDigit = lastDigit;
        this.day = day;
        this.hours = hours;
    }

    public Long getIdRestriction() {
        return idRestriction;
    }

    public void setIdRestriction(Long idRestriction) {
        this.idRestriction = idRestriction;
    }

    public int getLastDigit() {
        return lastDigit;
    }

    public void setLastDigit(int lastDigit) {
        this.lastDigit = lastDigit;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public CirculationHours getHours() {
        return hours;
    }

    public void setHours(CirculationHours hours) {
        this.hours = hours;
    }

    
    
}
