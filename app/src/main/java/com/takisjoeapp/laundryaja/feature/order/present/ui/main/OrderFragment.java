package com.takisjoeapp.laundryaja.feature.order.present.ui.main;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OnOrderUseCaseListener;
import com.takisjoeapp.laundryaja.feature.order.present.adapter.OrderAdapter;
import com.takisjoeapp.laundryaja.util.qrcode.QRUtil;
import com.takisjoeapp.laundryaja.util.qrcode.ScanQRActivity;

import java.util.List;

public class OrderFragment extends Fragment {

    private OrderViewModel mViewModel;
    private FloatingActionButton fabAddOrderPage;
    private RecyclerView rvOrder;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        fabAddOrderPage = view.findViewById(R.id.fabAddOrderPage);
        rvOrder = view.findViewById(R.id.rvOrder);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        mViewModel.showAllOrder(getViewLifecycleOwner(), new OnOrderUseCaseListener.ReadOrder() {
            @Override
            public void onSuccess(List<Order> orderList) {
                rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));
                rvOrder.setAdapter(new OrderAdapter(getActivity().getApplication(), orderList));
            }

            @Override
            public void onFailure(String Error) {
                Toast.makeText(getContext(), Error, Toast.LENGTH_SHORT).show();
            }
        });

        fabAddOrderPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(),ScanQRActivity.class));
//                QRUtil.scanBarcode(getActivity());
                Navigation.findNavController(v).navigate(R.id.action_orderFragment_to_addOrderFragment);
            }
        });
    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Log.d("QRUtil", "Cancelled");
//            } else {
//                Log.d("QRUtil", "Scanned");
//                String resultString = result.getContents();
//                Toast.makeText(getActivity(), resultString, Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }



}