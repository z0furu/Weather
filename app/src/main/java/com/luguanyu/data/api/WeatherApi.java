package com.luguanyu.data.api;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.luguanyu.data.api.listener.OnWeatherListener;
import com.luguanyu.data.database.AppDatabase;
import com.luguanyu.data.model.Weather;
import com.luguanyu.data.parser.ParserWeather;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class WeatherApi {

    private static final String TAG = "WeatherApi";

    private static final String URL = "http://www.cwb.gov.tw/rss/forecast/36_08.xml";

    private static volatile WeatherApi mInstance;

    private WeatherApi() {
    }

    public static WeatherApi getInstance() {
        if (mInstance == null) {
            synchronized (WeatherApi.class) {
                if (mInstance == null) {
                    mInstance = new WeatherApi();
                }
            }
        }
        return mInstance;
    }


    public void getWeather(final Context mContext, @NonNull final OnWeatherListener onWeatherListener) {
        Parser parser = new Parser();
        parser.execute(URL);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                for (int i = 0; i < list.size(); i++) {
                    Log.i(TAG, "onTaskCompleted: " + list.get(i).toString());
                    if (list.get(i).getTitle().contains("一週天氣預報")) {
                        List<Weather> weatherList = ParserWeather.parser(list.get(i));
                        AppDatabase database = Room.databaseBuilder(mContext, AppDatabase.class, "Weather").allowMainThreadQueries().build();
                        database.weatherDao().deleteAll();
                        for (Weather weather : weatherList) {
                            database.weatherDao().insert(weather);
                            Log.i(TAG, "onTaskCompleted: insert" );
                        }
                        onWeatherListener.onWeatherSuccess(weatherList);
                        return;
                    }

                }
            }

            @Override
            public void onError() {
                onWeatherListener.onWeatherError();
            }
        });
    }



}
