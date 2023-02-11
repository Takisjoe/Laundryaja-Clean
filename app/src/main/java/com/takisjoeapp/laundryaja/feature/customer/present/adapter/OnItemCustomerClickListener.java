package com.takisjoeapp.laundryaja.feature.customer.present.adapter;

import android.view.View;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

public interface OnItemCustomerClickListener {
    void onItemClick(View view, Customer customer);
}
