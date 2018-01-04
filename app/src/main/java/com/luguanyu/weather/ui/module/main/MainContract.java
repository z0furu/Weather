package com.luguanyu.weather.ui.module.main;

import com.luguanyu.data.model.Weather;

import java.util.List;


public interface MainContract {

    interface Presenter {
        void getDaily();
        void getWeather();
        void deleteWeather(Weather weather);
    }

    interface View {
        void showDaily(String title, String author);
        void showWeather(List<Weather> weatherList);
        void showError();
        void showProgress();
        void dismissProgress();
    }
}
