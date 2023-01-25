package com.takisjoeapp.laundryaja.feature.customer.data;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public interface CustomerData {

    boolean create(Customer customer);

    boolean update(String idCustomer, Customer customer);

    boolean delete(String idCustomer);

    List<Customer> read();
}