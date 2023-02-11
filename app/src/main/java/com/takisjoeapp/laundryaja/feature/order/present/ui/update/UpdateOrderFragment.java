package com.takisjoeapp.laundryaja.feature.order.present.ui.update;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.takisjoeapp.laundryaja.R;

public class UpdateOrderFragment extends Fragment {

    private UpdateOrderViewModel mViewModel;

    public static UpdateOrderFragment newInstance() {
        return new UpdateOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_order, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(UpdateOrderViewModel.class);
        // TODO: Use the ViewModel
    }

}