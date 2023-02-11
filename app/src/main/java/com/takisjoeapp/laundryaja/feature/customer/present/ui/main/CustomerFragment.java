package com.takisjoeapp.laundryaja.feature.customer.present.ui.main;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.data.database.RoomCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.repository.CustomerRepositoryImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.OnCustomerUseCaseListener;
import com.takisjoeapp.laundryaja.feature.customer.present.FragmentDataTransfer;
import com.takisjoeapp.laundryaja.feature.customer.present.adapter.CustomerAdapter;
import com.takisjoeapp.laundryaja.feature.customer.present.CustomerSharedViewModel;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFragment extends Fragment {

    private CustomerViewModel mViewModel;
    private RecyclerView rvListCustomer;
    private FloatingActionButton fabAddCustomerPage;
    private FragmentDataTransfer dataTransfer;
    public onDataPass dataPass;
    private CustomerSharedViewModel viewModel;


    @NonNull
    @Contract(" -> new")
    public static CustomerFragment newInstance() {
        return new CustomerFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataTransfer = (FragmentDataTransfer) context;
        Map<String, Object> map = new HashMap<>();


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_profile, container, false);
        //RecyclerView
        //Show All Customer
        rvListCustomer = view.findViewById(R.id.rvListCustomer);
        rvListCustomer.setLayoutManager(new LinearLayoutManager(getContext()));

        //Button
        fabAddCustomerPage = view.findViewById(R.id.fabAddCust);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);

        mViewModel.showAllCustomer(getViewLifecycleOwner(), new OnCustomerUseCaseListener.ReadCustomer() {
            @Override
            public void onSuccess(List<Customer> customerList) {
                Toast.makeText(getContext(), "isinya " + customerList.size(), Toast.LENGTH_SHORT).show();
                CustomerAdapter adapter = new CustomerAdapter(getActivity().getApplication(), customerList);
                rvListCustomer.setAdapter(adapter);
                adapter.setClickListener((view, customer) -> {
                    CustomerSharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(CustomerSharedViewModel.class);
                    sharedViewModel.selectCustomer(customer);
                    Navigation.findNavController(view).navigate(R.id.action_customerFragment_to_profileCustomerFragment);
                });
            }

            @Override
            public void onFailure(String Error) {

            }
        });

        //Navigasi menuju Halaman tambah customer
        fabAddCustomerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_customerFragment_to_addCustomerFragment);
            }
        });
    }


    public interface onDataPass {
        void passData(Customer customer);
    }

}
