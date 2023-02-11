package com.takisjoeapp.laundryaja.feature.order.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.data.OnOrderDataListener;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

/**
 * Interface OrderRepository menyediakan metode yang menangani operasi CRUD untuk entitas Order.
 * Implementasi dari metode-metode ini harus menangani akses data ke sumber data yang mendasar.
 *
 * @author Celvinanda Octiawan
 */
public interface OrderRepository {

    /**
     * Membuat sebuah Order
     *
     * @param order objek Order yang akan dibuat
     * @param createOrder interface callback untuk memberikan informasi sukses atau gagalnya proses pembuatan Order
     */
    void create(Order order, OnOrderDataListener.CreateOrder createOrder);

    /**
     * Method ini digunakan untuk memperbaharui data order yang sudah ada.
     *
     * @param order objek order yang ingin diperbaharui
     * @param updateOrder listener untuk mengetahui hasil dari proses pembaruan data
     */
    void update(Order order, OnOrderDataListener.UpdateOrder updateOrder);

    /**
     * Method ini digunakan untuk menghapus data order.
     *
     * @param order objek order yang ingin dihapus
     * @param deleteOrder listener untuk mengetahui hasil dari proses penghapusan data
     */
    void delete(Order order, OnOrderDataListener.DeleteOrder deleteOrder);

    /**
     * Method ini digunakan untuk mendapatkan daftar data order.
     *
     * @param readAll listener untuk mengetahui hasil dari proses pengambilan data
     */
    void readAll(OnOrderDataListener.ReadAll readAll);

    void drafOrder(Order order);

    void drafCustomer(Customer customer);

    void loadDraf(OnOrderDataListener.Draf draf);
}

