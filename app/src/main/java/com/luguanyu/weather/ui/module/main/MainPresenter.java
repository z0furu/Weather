package com.luguanyu.weather.ui.module.main;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.luguanyu.data.api.WeatherApi;
import com.luguanyu.data.api.listener.OnWeatherListener;
import com.luguanyu.data.database.AppDatabase;
import com.luguanyu.data.model.Weather;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class MainPresenter implements MainContract.Presenter, OnWeatherListener {

    private static final String TAG = "MainPresenter";

    private MainContract.View mainView;
    private Context mContext;

    private AppDatabase database;

    public MainPresenter(MainContract.View mainView, Context mContext) {
        this.mainView = mainView;
        this.mContext = mContext;
        database = Room.databaseBuilder(mContext, AppDatabase.class, "Weather").allowMainThreadQueries().build();
    }

    @Override
    public void getDaily() {
        try {
            URL url = new URL("http://www.appledaily.com.tw/index/dailyquote/");
            Document document = Jsoup.parse(url, 30000);
            Elements articleElements = document.select("article[class=dphs]");
            Elements dailyElements = articleElements.get(0).select("p");

            Elements authorElements = articleElements.get(0).select("h1");

            mainView.showDaily(dailyElements.get(0).text(), authorElements.get(0).text());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getWeather() {
        mainView.showProgress();
        WeatherApi.getInstance().getWeather(mContext, this);
    }

    @Override
    public void deleteWeather(Weather weather) {
        database.weatherDao().delete(weather);
    }

    @Override
    public void insertWeather(Weather weather) {
        database.weatherDao().insert(weather);
    }

    @Override
    public void onWeatherSuccess(List<Weather> weatherList) {
        mainView.showWeather(database.weatherDao().getAllWeather());
        mainView.dismissProgress();
    }

    @Override
    public void onWeatherError() {
        mainView.showError();
        mainView.dismissProgress();

    }
}
