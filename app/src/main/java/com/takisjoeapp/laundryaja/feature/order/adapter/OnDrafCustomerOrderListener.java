package com.takisjoeapp.laundryaja.feature.order.adapter;

import android.view.View;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

public interface OnDrafCustomerOrderListener {
    void onItemClick(View view, Customer customer);

}
