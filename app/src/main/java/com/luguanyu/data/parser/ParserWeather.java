package com.luguanyu.data.parser;

import android.util.Log;


import com.example.mydatabase.model.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class ParserWeather {

    private static final String TAG = "ParserWeather";

    public static List<Weather> parser(String[] descriptions) {
        List<Weather> weatherList = new ArrayList<>();

        for (int i = 0; i < descriptions.length; i++) {
            String[] content = descriptions[i].split(" ");
            if (content.length < 4) break;

            Weather weather = new Weather();
            weather.setUid(i);
            weather.setDate(content[0].trim());
            weather.setAMPM(content[1]);
            weather.setTemperature(content[2] + content[3] + content[4]);
            weather.setStatus(content[5]);
            weatherList.add(weather);
        }

        return weatherList;
    }
}
