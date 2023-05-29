package com.validacioncirculacion.picoplacabackend.entity;

import java.sql.Time;

public class Data {

    private int lastDigit;
    private String weekday;
    private Time hour;
    
    public Data(int lastDigit, String weekday, Time hour) {
        this.lastDigit = lastDigit;
        this.weekday = weekday;
        this.hour = hour;
    }

    public int getLastDigit() {
        return lastDigit;
    }

    public void setLastDigit(int lastDigit) {
        this.lastDigit = lastDigit;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }



    
}
