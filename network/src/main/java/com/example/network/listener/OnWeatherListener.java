package com.example.network.listener;

import com.prof.rssparser.Article;

import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public interface OnWeatherListener {
    void onWeatherSuccess(List<Article> weatherList);
    void onWeatherError();
}
