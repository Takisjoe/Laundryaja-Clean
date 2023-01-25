package com.takisjoeapp.laundryaja.feature.customer.present.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.present.adapter.CustomerAdapter;
import com.takisjoeapp.laundryaja.feature.customer.present.ui.add.AddCustomerFragment;

import org.jetbrains.annotations.Contract;

import java.util.List;

public class CustomerFragment extends Fragment {

    private CustomerViewModel mViewModel;
    private RecyclerView rvListCustomer;
    private FloatingActionButton fabAddCustomerPage;

    @NonNull
    @Contract(" -> new")
    public static CustomerFragment newInstance() {
        return new CustomerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_profile, container, false);
        rvListCustomer = view.findViewById(R.id.rvListCustomer);
        fabAddCustomerPage = view.findViewById(R.id.fabAddCustomerPage);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        //Mendapatkan daftar secara Real-time
//        NavController navController = Navigation.findNavController(view);
//        Navigation.setViewNavController(fabAddCustomerPage, navController);
        mViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                rvListCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
                rvListCustomer.setAdapter(new CustomerAdapter(customers));
            }
        });

        //Navigasi menuju Halaman tambah customer
        fabAddCustomerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Halo Gaes", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, AddCustomerFragment.newInstance())
                        .commit();
//                Navigation.findNavController(v).navigate(R.id.action_customerFragment_to_addCustomerFragment);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.getAllCustomers().observe(getViewLifecycleOwner(), new Observer<List<Customer>>() {
            @Override
            public void onChanged(List<Customer> customers) {
                rvListCustomer.setLayoutManager(new LinearLayoutManager(getContext()));
                rvListCustomer.setAdapter(new CustomerAdapter(customers));
            }
        });
    }
}