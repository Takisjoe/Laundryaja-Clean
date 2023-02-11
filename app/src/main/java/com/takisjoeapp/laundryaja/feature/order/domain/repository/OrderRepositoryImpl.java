package com.takisjoeapp.laundryaja.feature.order.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.data.OnOrderDataListener;
import com.takisjoeapp.laundryaja.feature.order.data.OrderData;
import com.takisjoeapp.laundryaja.feature.order.data.OrderDataImpl;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderData orderData;
    private final boolean isAccess;

    public OrderRepositoryImpl(User user, ModeService modeService) {
        orderData = new OrderDataImpl(user,modeService);
        if (modeService != ModeService.DEBUG) {
            isAccess = user.isAdmin(); //User harus admin untuk mengakses data
        }else {
            isAccess = false;
        }
    }

    @Override
    public void create(Order order, OnOrderDataListener.CreateOrder createOrder) {
        if (isAccess) {
            orderData.create(order,createOrder);
        }else {
            createOrder.onFailure("Akses data ditolak");
        }
    }

    @Override
    public void update(Order order, OnOrderDataListener.UpdateOrder updateOrder) {
        if (isAccess) {
            orderData.update(order, updateOrder);
        }else {
            updateOrder.onFailure("Akses data ditolak");
        }
    }

    @Override
    public void delete(Order order, OnOrderDataListener.DeleteOrder deleteOrder) {
        if (isAccess) {
            orderData.delete(order,deleteOrder);
        }else {
            deleteOrder.onFailure("Akses data ditolak");
        }
    }

    /**
     * Method ini digunakan untuk mendapatkan daftar data order.
     *
     * @param readAll listener untuk mengetahui hasil dari proses pengambilan data
     */
    @Override
    public void readAll(OnOrderDataListener.ReadAll readAll) {
        orderData.getData(readAll);
    }

    @Override
    public void drafOrder(Order order) {

    }

    @Override
    public void drafCustomer(Customer customer) {
        orderData.drafCustomer(customer);
    }

    @Override
    public void loadDraf(OnOrderDataListener.Draf draf) {

    }
}
