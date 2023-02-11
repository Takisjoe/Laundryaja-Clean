package com.takisjoeapp.laundryaja.feature.customer.present;


import java.util.Map;

public interface FragmentDataTransfer {
    void onDataTransfer(Map<String,Object> data);

    void getDataHost(OnDataHostListener dataHostListener);

    interface OnDataHostListener{
        void onData(Map<String,Object> transfer);
    }
}
