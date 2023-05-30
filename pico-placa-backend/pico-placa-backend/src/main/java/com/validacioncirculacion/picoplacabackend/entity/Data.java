package com.validacioncirculacion.picoplacabackend.entity;

import java.sql.Time;

public class Data {

    private int lastDigit;
    private String date;
    private Time hour;
    
    public Data(int lastDigit, String date, Time hour) {
        this.lastDigit = lastDigit;
        this.date = date;
        this.hour = hour;
    }

    public int getLastDigit() {
        return lastDigit;
    }

    public void setLastDigit(int lastDigit) {
        this.lastDigit = lastDigit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }
    
    

    
}
