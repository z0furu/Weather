package com.luguanyu.weather.ui.module.main;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.mydatabase.AppDatabase;
import com.example.mydatabase.MyDatabaseManager;
import com.example.mydatabase.model.Weather;
import com.example.network.api.WeatherApi;
import com.example.network.listener.OnTitleListener;
import com.example.network.listener.OnWeatherListener;
import com.luguanyu.data.parser.ParserWeather;

import java.net.URL;
import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class MainPresenter implements MainContract.Presenter, OnWeatherListener, OnTitleListener {

    private static final String TAG = "MainPresenter";

    private MainContract.View mainView;
    private WeatherApi weatherApi;
    private MyDatabaseManager myDatabaseManager;

    public MainPresenter(MainContract.View mainView, WeatherApi weatherApi, MyDatabaseManager myDatabaseManager) {
        this.mainView = mainView;
        this.weatherApi = weatherApi;
        this.myDatabaseManager = myDatabaseManager;
    }

    @Override
    public void getDaily() {
        weatherApi.getTitle(this);
    }

    @Override
    public void getWeather() {
        mainView.showProgress();
        weatherApi.getWeather(this);
    }

    @Override
    public void deleteWeather(Weather weather) {
        myDatabaseManager.deleteWeather(weather);
    }

    @Override
    public void insertWeather(Weather weather) {
        myDatabaseManager.insertWeather(weather);
    }



    @Override
    public void onWeatherSuccess(String[] descriptions) {
        List<Weather> weatherList = ParserWeather.parser(descriptions);
        myDatabaseManager.insertWeather(weatherList);

        mainView.showWeather(myDatabaseManager.getAllWeather());
        mainView.dismissProgress();
    }

    @Override
    public void onWeatherError() {
        mainView.showError();
        mainView.dismissProgress();

    }

    @Override
    public void onTitle(String title, String author) {
        mainView.showDaily(title, author);
    }

    @Override
    public void onFailed() {
        mainView.showDaily("", "");
    }
}
