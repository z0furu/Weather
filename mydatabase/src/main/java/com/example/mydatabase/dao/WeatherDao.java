package com.example.mydatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mydatabase.model.Weather;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM Weather")
    List<Weather> getAllWeather();

    @Insert
    void insert(Weather... weather);

    @Delete
    void delete(Weather weather);

    @Query("DELETE FROM Weather")
    void deleteAll();
}
