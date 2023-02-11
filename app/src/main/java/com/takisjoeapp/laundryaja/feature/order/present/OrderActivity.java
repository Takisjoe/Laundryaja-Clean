package com.takisjoeapp.laundryaja.feature.order.present;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.order.present.ui.main.OrderFragment;
import com.takisjoeapp.laundryaja.util.qrcode.QRUtil;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ServiceLocator.registerService("OrderActivity", getApplication());

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.container, OrderFragment.newInstance())
//                    .commitNow();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, QRUtil.handleScanResult(requestCode, resultCode, data), Toast.LENGTH_SHORT).show();
    }
}