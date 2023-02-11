package com.takisjoeapp.laundryaja.feature.customer.present;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

public class CustomerSharedViewModel extends ViewModel {
    private final MutableLiveData<Customer> selectedCustomer = new MutableLiveData<>();

    public void selectCustomer(Customer customer) {
        selectedCustomer.setValue(customer);
    }

    public LiveData<Customer> getSelectedCustomer() {
        return selectedCustomer;
    }
}
