package com.luguanyu.data.api.listener;

import com.luguanyu.data.model.Weather;

import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public interface OnWeatherListener {
    void onWeatherSuccess(List<Weather> weatherList);
    void onWeatherError();
}
