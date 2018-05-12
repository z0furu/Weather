package com.example.mydatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mydatabase.dao.WeatherDao;
import com.example.mydatabase.model.Weather;

/**
 * Created by luguanyu on 2018/1/3.
 */

@Database(entities = {Weather.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
