package com.luguanyu.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.luguanyu.data.dao.WeatherDao;
import com.luguanyu.data.model.Weather;

/**
 * Created by luguanyu on 2018/1/3.
 */

@Database(entities = {Weather.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
