package com.takisjoeapp.laundryaja.feature.customer.present.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCaseImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.OnCustomerUseCaseListener;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.List;

public class CustomerViewModel extends AndroidViewModel {
    private final CustomerUseCase customerUseCase;

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerUseCase = new CustomerUseCaseImpl(ModeService.RULES);
    }

    public void showAllCustomer(LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.ReadCustomer readCustomer) {
        customerUseCase.showAllCustomer(lifecycleOwner,readCustomer);
    }

}