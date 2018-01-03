package com.luguanyu.data.parser;

import android.util.Log;

import com.luguanyu.data.model.Weather;
import com.prof.rssparser.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class ParserWeather {

    private static final String TAG = "ParserWeather";

    public static List<Weather> parser(Article article) {
        List<Weather> weatherList = new ArrayList<>();
        String[] brSplit = article.getDescription().split("<BR>");
        for (String strWeather: brSplit) {
            String[] content = strWeather.split(" ");
            if (content.length < 4) break;

            Weather weather = new Weather();
            weather.setDate(content[0].trim());
            weather.setAMPM(content[1]);
            weather.setTemperature(content[2] + content[3] + content[4]);
            weather.setStatus(content[5]);
            weatherList.add(weather);

        }
        return weatherList;
    }
}
