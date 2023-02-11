package com.takisjoeapp.laundryaja;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.takisjoeapp.laundryaja.feature.customer.data.CustomerDataImpl;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.FirestoreCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.OnQueryDataCustomer;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.present.CustomerActivity;
import com.takisjoeapp.laundryaja.feature.order.data.OrderDataImpl;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OnOrderUseCaseListener;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCase;
import com.takisjoeapp.laundryaja.feature.order.domain.usecase.OrdersUseCaseImpl;
import com.takisjoeapp.laundryaja.feature.order.helper.OrderBuilder;
import com.takisjoeapp.laundryaja.feature.order.present.OrderActivity;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView ivSplashScreen;

    class Booting {
        public String urlImageScreen = "https://img.freepik.com/free-psd/real-estate-house-property-instagram-facebook-story-template_120329-1893.jpg?w=1080&t=st=1675868565~exp=1675869165~hmac=9f1d7115d9609bbd3c916d403ae531e547c38f627adf2c6df7a8b0b8dd9dc445";

        public Booting() {
            loadCustomer();
            loadOrder();
        }

        public void loadCustomer() {
            new CustomerDataImpl(getApplication());
        }

        public void loadOrder() {
            new OrderDataImpl(getApplication());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        ivSplashScreen = findViewById(R.id.ivSplashScreen);
        Picasso.get()
                .load(new Booting().urlImageScreen)
                .into(ivSplashScreen);

        new Booting();

        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                finish();
            }
        }.start();


//        testing();
    }

    private void testing() {
        Customer customer = new Customer(
                "id",
                "name",
                "username",
                "phone",
                "address",
                "email",
                "password",
                "idLaundry",
                "idBranch",
                "role",
                "uid",
                "photo",
                "status",
                "registrationToken",
                true,
                true,
                true,
                null,
                null,
                true,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        Order order = new OrderBuilder().build();

        OrdersUseCase ordersUseCase = new OrdersUseCaseImpl(ModeService.RULES);
        ordersUseCase.createOrder(customer, order, new OnOrderUseCaseListener.CreateOrder() {
            @Override
            public void onSuccess(Order order) {
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String Error) {
                Toast.makeText(MainActivity.this, "Gagal " + Error, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
