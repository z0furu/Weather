package com.example.mydatabase.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Weather")
public class Weather {

    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "AMPM")
    private String AMPM;

    @ColumnInfo(name = "temperature")
    private String temperature;

    @ColumnInfo(name = "status")
    private String status;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
                "uid=" + uid +
                ", date='" + date + '\'' +
                ", AMPM='" + AMPM + '\'' +
                ", temperature='" + temperature + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
