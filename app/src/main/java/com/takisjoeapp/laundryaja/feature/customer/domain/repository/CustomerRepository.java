package com.takisjoeapp.laundryaja.feature.customer.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.data.OnCustomerDataListener;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.data.OnOrderDataListener;

public interface CustomerRepository {

    /**
     * Method ini digunakan untuk membuat baru data customer.
     *
     * @param customer untuk memberikan data yang akan dimasukan kedalam data
     * @param create listener untuk mengetahui hasil dari proses pengambilan data
     */
    void create(Customer customer, OnCustomerDataListener.Create create);

    void update(Customer customer, OnCustomerDataListener.Update update);

    void delete(Customer customer, OnCustomerDataListener.Delete delete);

    /**
     * Method ini digunakan untuk mendapatkan daftar data customer.
     *
     * @param readAll listener untuk mengetahui hasil dari proses pengambilan data
     */
    void readAll(OnCustomerDataListener.ReadAll readAll);

    void searchByPhoneServer(String search, OnCustomerDataListener.ReadAll readAll);

    void drafCustomer(Customer customer);

    void loadDraf(OnCustomerDataListener.Draf draf);
}
