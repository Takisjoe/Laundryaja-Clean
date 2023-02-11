package com.takisjoeapp.laundryaja.feature.customer.present;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.data.CustomerDataImpl;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.HashMap;
import java.util.Map;

public class CustomerActivity extends AppCompatActivity implements FragmentDataTransfer{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        ServiceLocator.registerService("CustomerActivity", this);


        CustomerSharedViewModel sharedViewModel = new ViewModelProvider(this).get(CustomerSharedViewModel.class);
//        String data = sharedViewModel.getData().getValue();
    }



    @Override
    public void onDataTransfer(Map<String, Object> data) {

    }

    @Override
    public void getDataHost(OnDataHostListener dataHostListener) {
        HashMap map = new HashMap<>();
        map.put("A", "Celvinanda namaku");
        map.put("B", "Saya Ganteng");
        dataHostListener.onData(map);
    }
}