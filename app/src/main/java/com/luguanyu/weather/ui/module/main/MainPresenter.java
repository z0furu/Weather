package com.luguanyu.weather.ui.module.main;

import com.luguanyu.data.api.WeatherApi;
import com.luguanyu.data.api.listener.OnWeatherListener;
import com.luguanyu.data.model.Weather;

import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class MainPresenter implements MainContract.Presenter, OnWeatherListener {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getWeather() {
        mainView.showProgress();
        WeatherApi.getInstance().getWeather(this);
    }

    @Override
    public void onWeatherSuccess(List<Weather> weatherList) {
        mainView.showWeather(weatherList);
        mainView.dismissProgress();
    }

    @Override
    public void onWeatherError() {
        mainView.dismissProgress();

    }
}
