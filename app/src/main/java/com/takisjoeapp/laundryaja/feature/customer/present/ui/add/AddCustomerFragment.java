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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.takisjoeapp.laundryaja.R;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.OnCustomerUseCaseListener;
import com.takisjoeapp.laundryaja.util.address.Address;
import com.takisjoeapp.laundryaja.util.address.AddressSheetButtom;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class AddCustomerFragment extends Fragment {
    private AddCustomerViewModel mViewModel;
    private Button btnAddCustomer;

    private TextInputLayout tilNameAddCustomer, tilPhoneAddCustomer, tilAddressAddCustomer;
    private TextInputEditText tieNameAddCustomer, tiePhoneAddCustomer, tieAddressAddCustomer;

    private String idAlamat;

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
        tieNameAddCustomer = view.findViewById(R.id.tieNameAddCustomer);
        tiePhoneAddCustomer = view.findViewById(R.id.tiePhoneAddCustomer);
        tieAddressAddCustomer = view.findViewById(R.id.tieAddressAddCustomer);
        tilNameAddCustomer = view.findViewById(R.id.tilNameAddCustomer);
        tilPhoneAddCustomer = view.findViewById(R.id.tilPhoneAddCustomer);
        tilAddressAddCustomer = view.findViewById(R.id.tilAddressAddCustomer);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddCustomerViewModel.class);
        // TODO: Use the ViewModel
        tieAddressAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddressSheetButtom(getActivity().getApplication(), new AddressSheetButtom.OnShowListener() {
                    @Override
                    public void onAddress(String id,String address) {
                        tieAddressAddCustomer.setText(address);
                        idAlamat = id;
                    }

                    @Override
                    public void onAddressComplete(Address provinsi, Address kota, Address kecamatan, Address kelurahan, String detail) {

                    }
                }).show(getParentFragmentManager(),null);
            }
        });

        //Aksi tombol menambah customer
        btnAddCustomer.setOnClickListener(v -> {
            String nama = Objects.requireNonNull(tieNameAddCustomer.getText()).toString();
            String phone = Objects.requireNonNull(tiePhoneAddCustomer.getText()).toString();

            mViewModel.addCustomer(nama, phone, idAlamat, new OnCustomerUseCaseListener.Create() {
                @Override
                public void onSuccess(Customer customer) {
                    getActivity().onBackPressed();
                }

                @Override
                public void onFailure(String Error) {
                    if (Error.contains("Nama")) {
                        tilNameAddCustomer.setError(Error);
                    }
                }
            });

        });
    }

}