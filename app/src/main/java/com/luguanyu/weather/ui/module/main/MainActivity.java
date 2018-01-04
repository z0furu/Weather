package com.luguanyu.weather.ui.module.main;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.luguanyu.data.database.AppDatabase;
import com.luguanyu.data.model.Weather;
import com.luguanyu.weather.R;
import com.luguanyu.weather.ui.WeatherAdapter;
import com.luguanyu.weather.ui.listener.OnWeatherClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, SwipeRefreshLayout.OnRefreshListener, OnWeatherClickListener {

    private static final String TAG = "MainActivity";

    private MainPresenter mainPresenter;
    private Group weatherGroup;
    private SwipeRefreshLayout weatherSwipe;
    private RecyclerView weatherRecyclerView;
    private ProgressBar progressBar;
    private TextView txtTitle, txtAuthor;

    private WeatherAdapter weatherAdapter;
    private List<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mainPresenter = new MainPresenter(this,this);
        mainPresenter.getWeather();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mainPresenter.getDaily();
            }
        }).start();

    }

    private void initView() {
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtAuthor = (TextView) findViewById(R.id.txt_author);
        weatherGroup = (Group) findViewById(R.id.weather_group);
        weatherSwipe = (SwipeRefreshLayout) findViewById(R.id.weather_swipe);
        weatherRecyclerView = (RecyclerView) findViewById(R.id.weather_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        initRecyclerView();
    }

    private void initRecyclerView() {

        weatherSwipe.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        weatherSwipe.canChildScrollUp();
        weatherSwipe.setOnRefreshListener(this);

        weatherRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        weathers = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this, weathers);
        weatherRecyclerView.setAdapter(weatherAdapter);

    }

    @Override
    public void showDaily(final String title, final String author) {
        txtTitle.post(new Runnable() {
            @Override
            public void run() {
                txtTitle.setText(title);
                txtAuthor.setText(author);
            }
        });
    }

    @Override
    public void showWeather(List<Weather> weatherList) {
        weatherSwipe.setRefreshing(false);
        weathers = weatherList;
        weatherAdapter.clear();
        weatherAdapter.setWeatherList(weatherList);
    }

    @Override
    public void showError() {
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setCancelable(false)
                .setMessage("連線錯誤")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .show();
    }

    @Override
    public void showProgress() {
        weatherGroup.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        weatherGroup.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        if (progressBar.getVisibility() == View.GONE) {
            mainPresenter.getWeather();
            weatherSwipe.setRefreshing(true);
        }
    }

    @Override
    public void onItemClick(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("警告")
                .setCancelable(false)
                .setMessage("確定要刪除？")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mainPresenter.deleteWeather(weathers.get(position));
                        weathers.remove(position);
                        weatherAdapter.notifyItem(position);
                    }
                })
                .setNeutralButton("取消", null)
                .show();


    }
}
