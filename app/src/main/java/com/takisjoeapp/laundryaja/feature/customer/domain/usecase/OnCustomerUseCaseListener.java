package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public class OnCustomerUseCaseListener {
    public interface Create {
        void onSuccess(Customer customer);

        void onFailure(String Error);
    }

    public interface ReadCustomer {
        void onSuccess(List<Customer> customerList);

        void onFailure(String Error);
    }

    public interface SearchLocalCustomer {
        void onSuccess(List<Customer> customerList);

        void onFailure(String Error);
    }

    public interface SearchCustomers {
        void onLoad(boolean isLoad,int detik);

        void onCustomersResult(List<Customer> customerList);

        void onFailure(String Error);
    }
}
