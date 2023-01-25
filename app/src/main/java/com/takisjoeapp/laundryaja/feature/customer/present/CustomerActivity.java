package com.takisjoeapp.laundryaja.feature.customer.present;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.present.ui.main.CustomerFragment;

public class CustomerActivity extends AppCompatActivity {

    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, CustomerFragment.newInstance())
                    .commitNow();
        }
    }
}