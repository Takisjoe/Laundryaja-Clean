package com.takisjoeapp.laundryaja.feature.order.data;

import androidx.lifecycle.LiveData;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

public interface OrderData {

    Customer drafCustomer();

    void drafCustomer(Customer customer);

    Order drafOrder();

    void drafOrder(Order order);

    void create(Order order, OnOrderDataListener.CreateOrder orderListener); //Testing OK

    void update(Order order, OnOrderDataListener.UpdateOrder updateOrder);

    void delete(Order order, OnOrderDataListener.DeleteOrder deleteOrder);

    void getData(OnOrderDataListener.GetData getData);

    void getData(OnOrderDataListener.ReadAll readAll);

    LiveData<List<Order>> getData();


}
