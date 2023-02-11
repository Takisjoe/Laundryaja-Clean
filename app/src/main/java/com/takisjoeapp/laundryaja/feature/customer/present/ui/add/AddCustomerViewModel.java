package com.takisjoeapp.laundryaja.feature.customer.present.ui.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCaseImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.OnCustomerUseCaseListener;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

public class AddCustomerViewModel extends AndroidViewModel {
    private final CustomerUseCase customerUseCase;

    public AddCustomerViewModel(@NonNull Application application) {
        super(application);
        customerUseCase = new CustomerUseCaseImpl(ModeService.RULES);
    }

    // TODO: Implement the ViewModel
    public void addCustomer(String name, String phone, String address, OnCustomerUseCaseListener.Create create) {
        customerUseCase.addCustomer(name,phone,address,create);
    }
}