package com.luguanyu.weather.ui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.luguanyu.data.model.Weather;
import com.luguanyu.weather.R;

import java.util.List;

/**
 * Created by luguanyu on 2018/1/3.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private static final String TAG = "WeatherAdapter";

    private List<Weather> weatherList;

    public WeatherAdapter(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtAmPm.setText(weatherList.get(position).getAMPM());
        holder.txtDate.setText(weatherList.get(position).getDate());
        holder.txtStatus.setText(weatherList.get(position).getStatus());
        holder.txtTemperature.setText(weatherList.get(position).getTemperature());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void clear() {
        weatherList.clear();
        notifyDataSetChanged();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDate;
        private TextView txtAmPm;
        private TextView txtTemperature;
        private TextView txtStatus;

        MyViewHolder(View itemView) {
            super(itemView);
            txtDate = (TextView) itemView.findViewById(R.id.txt_date);
            txtAmPm = (TextView) itemView.findViewById(R.id.txt_am_pm);
            txtTemperature = (TextView) itemView.findViewById(R.id.txt_temperature);
            txtStatus = (TextView) itemView.findViewById(R.id.txt_status);
        }
    }
}
