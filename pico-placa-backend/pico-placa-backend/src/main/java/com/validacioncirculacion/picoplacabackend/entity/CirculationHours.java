package com.validacioncirculacion.picoplacabackend.entity;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "circulation_hours")
public class CirculationHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHours;
    @Column(name = "startTimeMorning")
    private Time startTimeMorning;
    @Column(name = "endTimeMorning")
    private Time endTimeMorning;
    @Column(name = "startTimeNight")
    private Time startTimeNight;
    @Column(name="endTimeNight")
    private Time endTimeNight;

    public CirculationHours(){

    }

    public Long getIdHours() {
        return idHours;
    }

    public void setIdHours(Long idHours) {
        this.idHours = idHours;
    }

    public Time getStartTimeMorning() {
        return startTimeMorning;
    }

    public void setStartTimeMorning(Time startTimeMorning) {
        this.startTimeMorning = startTimeMorning;
    }

    public Time getEndTimeMorning() {
        return endTimeMorning;
    }

    public void setEndTimeMorning(Time endTimeMorning) {
        this.endTimeMorning = endTimeMorning;
    }

    public Time getStartTimeNight() {
        return startTimeNight;
    }

    public void setStartTimeNight(Time startTimeNight) {
        this.startTimeNight = startTimeNight;
    }

    public Time getEndTimeNight() {
        return endTimeNight;
    }

    public void setEndTimeNight(Time endTimeNight) {
        this.endTimeNight = endTimeNight;
    }

    

    
}
