package com.luguanyu.weather.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.luguanyu.weather.ui.listener.OnSwipeListener;

/**
 * Created by luguanyu on 2018/1/4.
 */

public class WeatherItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private OnSwipeListener onSwipeListener;

    public WeatherItemTouchHelperCallback(OnSwipeListener onSwipeListener) {
        this.onSwipeListener = onSwipeListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(0, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        onSwipeListener.onSwipeItem(viewHolder.getAdapterPosition());
    }
}
