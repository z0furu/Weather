package com.luguanyu.weather.ui.module.main;

import com.luguanyu.data.model.Weather;

import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public interface MainContract {

    interface Presenter {
        void getWeather();
    }

    interface View {
        void showWeather(List<Weather> weatherList);
        void showError();
        void showProgress();
        void dismissProgress();
    }
}
