package com.takisjoeapp.laundryaja.feature.customer.present.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.data.database.FirestoreCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.present.ui.main.CustomerFragment;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import org.jetbrains.annotations.Contract;

public class AddCustomerFragment extends Fragment {

    private AddCustomerViewModel mViewModel;
    private Button btnAddCustomer;

    @NonNull
    @Contract(" -> new")
    public static AddCustomerFragment newInstance() {
        return new AddCustomerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_customer, container, false);
        btnAddCustomer = view.findViewById(R.id.btnInsertAddCustomer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ServiceLocator.registerService("addCustomer", getActivity());
        mViewModel = new ViewModelProvider(this).get(AddCustomerViewModel.class);
        // TODO: Use the ViewModel


        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = "Celvinanda Octiawan";
                String phone = "081338332023";
                String alamat = "Binangun";

                mViewModel.addCustomer(nama, phone, alamat, new CustomerUseCase.OnAddCustomerCallback() {
                    @Override
                    public void onSuccess(boolean isSuccess) {
//                        getActivity().onBackPressed();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, CustomerFragment.newInstance())
                                .commit();
                    }

                    @Override
                    public void onFailed(String message) {
                        System.out.println(message);
                    }
                });
            }
        });
    }

}