package com.takisjoeapp.laundryaja.feature.order.domain.usecase;

import androidx.lifecycle.LifecycleOwner;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

/**
 * OrdersUseCase adalah interface yang menyediakan metode untuk membuat pesanan.
 *
 * @author Celvinanda Octiawan
 * @version 1.0
 */
public interface OrdersUseCase {

    /**
     * Method untuk membuat order baru
     *
     * @param customer    instance dari kelas {@link Customer} yang memiliki informasi customer
     * @param order       instance dari kelas {@link Order} yang memiliki informasi order
     * @param createOrder instance dari interface {@link OnOrderUseCaseListener.CreateOrder} untuk memberikan response
     *                    setelah proses pembuatan order selesai
     */
    void createOrder(Customer customer, Order order, OnOrderUseCaseListener.CreateOrder createOrder);

    void drafCustomer(String search, LifecycleOwner lifecycleOwner, OnOrderUseCaseListener.DrafCustomerOrder customerOrder);

    void drafCustomer(Customer customer, OnOrderUseCaseListener.DrafNewCustomerOrder newCustomerOrder);

    void drafOrder(Order order);

    /**
     * Method untuk menampilkan semua order
     *
     * @param readOrder instance dari interface {@link OnOrderUseCaseListener.ReadOrder} untuk memberikan response setelah proses pengambilan data order selesai
     */
    void showAllOrder(LifecycleOwner owner, OnOrderUseCaseListener.ReadOrder readOrder);
}