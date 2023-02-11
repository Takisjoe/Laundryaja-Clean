package com.takisjoeapp.laundryaja.feature.order.present.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;

import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OnOrderUseCaseListener;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCase;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCaseImpl;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

public class OrderViewModel extends AndroidViewModel {
    private final OrdersUseCase ordersUseCase;
    public OrderViewModel(@NonNull Application application) {
        super(application);

        ordersUseCase = new OrdersUseCaseImpl(ModeService.RULES);
    }

    public void showAllOrder(LifecycleOwner lifecycleOwner, OnOrderUseCaseListener.ReadOrder readOrder) {
        ordersUseCase.showAllOrder(lifecycleOwner,readOrder);
    }
}