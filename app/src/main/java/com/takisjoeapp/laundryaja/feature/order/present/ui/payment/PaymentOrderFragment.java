package com.takisjoeapp.laundryaja.feature.order.present.ui.payment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takisjoeapp.laundryaja.R;

public class PaymentOrderFragment extends Fragment {

    private PaymentOrderViewModel mViewModel;

    public static PaymentOrderFragment newInstance() {
        return new PaymentOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PaymentOrderViewModel.class);
        // TODO: Use the ViewModel
    }

}