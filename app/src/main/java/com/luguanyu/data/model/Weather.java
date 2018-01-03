package com.luguanyu.data.model;

public class Weather {

    private String date;
    private String AMPM;
    private String temperature;
    private String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAMPM() {
        return AMPM;
    }

    public void setAMPM(String AMPM) {
        this.AMPM = AMPM;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date='" + date + '\'' +
                ", AMPM='" + AMPM + '\'' +
                ", temperature='" + temperature + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
