package com.takisjoeapp.laundryaja.feature.customer.data.database.firebase;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

public interface OnNotifyDataCustomer {
    void onNotify(Customer customer);

    void onNotifyModify(Customer customer);

    void onNotifyRemove(Customer customer);

    void onFailure(Exception e);
}
