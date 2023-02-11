package com.takisjoeapp.laundryaja.feature.order.present.adapter;

import android.view.View;

import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

public interface OnItemOrderClickListener {
    void onItemClick(View view, Order order);
}
