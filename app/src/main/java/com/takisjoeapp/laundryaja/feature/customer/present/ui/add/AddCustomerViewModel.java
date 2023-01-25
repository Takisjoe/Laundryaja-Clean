package com.takisjoeapp.laundryaja.feature.customer.present.ui.add;

import androidx.lifecycle.ViewModel;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUsecaseImpl;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

public class AddCustomerViewModel extends ViewModel {
    private final CustomerUseCase customerUseCase;

    public AddCustomerViewModel() {
        customerUseCase = new CustomerUsecaseImpl();
    }
    // TODO: Implement the ViewModel

    public void addCustomer(String name,String phone,String address,CustomerUseCase.OnAddCustomerCallback customerCallback) {
        Customer customer = new CustomerBuilder()
                .setId(String.valueOf(TimeUtils.getTimestamp()))
                .setName(name)
                .setAddress(address)
                .setPhone(phone)
                .setUsername(name+"@laundryaja.com")
                .build();

        customerUseCase.addCustomer(customer,customerCallback);
    }
}