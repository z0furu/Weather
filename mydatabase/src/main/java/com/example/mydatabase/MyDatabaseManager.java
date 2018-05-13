package com.example.mydatabase;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.mydatabase.dao.WeatherDao;
import com.example.mydatabase.model.Weather;

import java.util.List;

/**
 * Created by wade on 2018/5/12.
 */

public class MyDatabaseManager {

    private static volatile MyDatabaseManager mInstance;
    private Context context;
    private AppDatabase database;

    public MyDatabaseManager(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, "Weather").allowMainThreadQueries().build();
    }


    public static MyDatabaseManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MyDatabaseManager.class) {
                if (mInstance == null) {
                    mInstance = new MyDatabaseManager(context);
                }
            }
        }
        return mInstance;
    }

   public void insertWeather(List<Weather> weatherList) {
       WeatherDao weatherDao = database.weatherDao();
       weatherDao.deleteAll();
       for (Weather weather : weatherList) {
            weatherDao.insert(weather);
       }
   }

    public void insertWeather(Weather weather) {
        WeatherDao weatherDao = database.weatherDao();
        weatherDao.insert(weather);
    }

    public void deleteWeather(Weather weather) {
        WeatherDao weatherDao = database.weatherDao();
        weatherDao.delete(weather);
    }

    public List<Weather> getAllWeather() {
        WeatherDao weatherDao = database.weatherDao();
        return weatherDao.getAllWeather();
    }
}
