package com.takisjoeapp.laundryaja.feature.order.present.ui.add;

import android.app.Application;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OnOrderUseCaseListener;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCase;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCaseImpl;
import com.takisjoeapp.laundryaja.feature.order.helper.OrderBuilder;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

public class CreateOrderViewModel extends AndroidViewModel {
    private final OrdersUseCase ordersUseCase;

    public CreateOrderViewModel(@NonNull Application application) {
        super(application);
        ordersUseCase = new OrdersUseCaseImpl(ModeService.RULES);
    }

    public void createOrder(String idCustomer, long orderDate, long completionDate, String catatan, OnOrderUseCaseListener.CreateOrder onOrderUseCaseListener) {
        //Customer
        Customer customer = new CustomerBuilder()
                .setId(idCustomer)
                .build();

        //Order
        Order order = new OrderBuilder()
                .setOrderDate(orderDate)
                .setCompletionDate(completionDate)
                .setCatatan(catatan)
                .build();

        ordersUseCase.createOrder(customer, order, onOrderUseCaseListener);
    }

    public TextWatcher selectCustomer(LifecycleOwner lifecycleOwner, OnOrderUseCaseListener.DrafCustomerOrder drafCustomerOrder) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ordersUseCase.drafCustomer(s.toString(), lifecycleOwner, drafCustomerOrder);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void addNewCustomer(String nama, String phone, String alamat, OnOrderUseCaseListener.DrafNewCustomerOrder newCustomerOrder) {
        Customer customer = new CustomerBuilder()
                .setName(nama)
                .setPhone(phone)
                .setAddress(alamat)
                .build();
        ordersUseCase.drafCustomer(customer, newCustomerOrder);
    }

    public void saveOrder(Long completeDate, String catatan) {
        Order order = new OrderBuilder()
                .setCompletionDate(completeDate)
                .setCatatan(catatan)
                .build();
        ordersUseCase.drafOrder(order);
    }
}
