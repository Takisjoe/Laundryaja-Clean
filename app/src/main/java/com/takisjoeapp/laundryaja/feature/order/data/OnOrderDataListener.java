package com.takisjoeapp.laundryaja.feature.order.data;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

public class OnOrderDataListener {

    public interface CreateOrder {
        void onSuccess();

        void onFailure(String error);
    }

    public interface UpdateOrder {
        void onSuccess();

        void onFailure(String error);
    }

    public interface DeleteOrder {
        void onSuccess();

        void onFailure(String error);
    }

    public interface GetData {
        void onNotify(Order order);

        void onNotifyModify(Order order);

        void onNotifyRemove(Order order);

        void onFailure(String error);
    }

    public interface ReadAll {
        void onSuccess(List<Order> orderList);

        void onFailure(String error);
    }

    public interface Draf {
        void onLoad(Customer customer, Order order);

        void onFailure(String Error);
    }

}
