package com.takisjoeapp.laundryaja.feature.customer.data;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

public interface CustomerData {
    String DI_LifecycleOwner = "Customer-lifecycleOwner";
    String DI_Application = "Customer-application";

    void create(Customer customer, OnCustomerDataListener.Create create);

    void update(Customer customer, OnCustomerDataListener.Update update);

    void delete(Customer customer, OnCustomerDataListener.Delete delete);

    void search(String value, OnCustomerDataListener.Search search);

    void getData(OnCustomerDataListener.GetData getData);

    void getData(OnCustomerDataListener.ReadAll readAll);
}
