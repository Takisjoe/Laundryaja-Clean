package com.takisjoeapp.laundryaja.feature.order.domain.usecase;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.adapter.DrafCustomerOrderAdapter;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

public class OnOrderUseCaseListener {

    public interface CreateOrder {
        void onSuccess(Order order);

        void onFailure(String Error);
    }

    public interface ReadOrder {
        void onSuccess(List<Order> orderList);

        void onFailure(String Error);
    }

    public interface DrafCustomerOrder {
        void onSuccess(DrafCustomerOrderAdapter adapter);

        void onLoad(boolean isLoad, int detik);

        void onSelected(Customer customer, boolean isSelected, int count);

        void onFailure(String Error);
    }

    public interface DrafNewCustomerOrder {
        void onSuccess(Customer customer);

        void onFailure(String Error);
    }
}
