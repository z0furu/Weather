package com.example.network.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.network.listener.OnTitleListener;
import com.example.network.listener.OnWeatherListener;
import com.prof.rssparser.Article;
import com.prof.rssparser.Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;

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


    public void getWeather(@NonNull final OnWeatherListener onWeatherListener) {
        Parser parser = new Parser();
        parser.execute(URL);
        parser.onFinish(new Parser.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(ArrayList<Article> list) {
                for (int i = 0; i < list.size(); i++) {
                    Log.i(TAG, "onTaskCompleted: " + list.get(i).toString());
                    Article article = list.get(i);
                    if (article.getTitle().contains("一週天氣預報")) {
                        onWeatherListener.onWeatherSuccess(article.getDescription().split("<BR>"));
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

    public void getTitle(@NonNull OnTitleListener onTitleListener) {
        try {
            java.net.URL url = new URL("http://www.appledaily.com.tw/index/dailyquote/");

            Document document = Jsoup.parse(url, 30000);
            Elements articleElements = document.select("article[class=dphs]");
            Elements dailyElements = articleElements.get(0).select("p");

            Elements authorElements = articleElements.get(0).select("h1");

            onTitleListener.onTitle(dailyElements.get(0).text(), authorElements.get(0).text());
        } catch (Exception e) {
            e.printStackTrace();
            onTitleListener.onFailed();
        }
    }

}
