package com.validacioncirculacion.picoplacabackend.entity;


public class Data {

    private int lastDigit;
    private String date;
    private String hour;



    
    public Data(int lastDigit, String date, String hour) {
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
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }
    
    
    

    
}
