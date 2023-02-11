package com.takisjoeapp.laundryaja.feature.order.data.database.firebase;

import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

public interface OnNotifyDataOrder {
    void onNotify(Order order);

    void onNotifyModify(Order order);

    void onNotifyRemove(Order order);

    void onFailure(String error);
}
