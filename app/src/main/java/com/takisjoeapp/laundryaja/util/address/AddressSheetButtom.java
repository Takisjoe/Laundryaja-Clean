package com.takisjoeapp.laundryaja.util.address;

import android.app.Application;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.takisjoeapp.laundryaja.R;

import java.util.ArrayList;
import java.util.List;

public class AddressSheetButtom extends BottomSheetDialogFragment {
    private final AddressUtils utils;
    private final Application application;
    private final OnShowListener showListener;

    private Spinner spinnerKecamatan;
    private Spinner spinnerKota;
    private Spinner spinnerKelurahan;
    private TextView tvAddress;


    public AddressSheetButtom(Application application, OnShowListener showListener) {
        this.application = application;
        this.showListener = showListener;
        utils = new AddressUtils(application);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_bottom_wilayah, container, false);
        Spinner spinnerProvinsi = view.findViewById(R.id.spinnerProvinsi);
        spinnerKota = view.findViewById(R.id.spinnerKota);
        spinnerKecamatan = view.findViewById(R.id.spinnerKecamatan);
        spinnerKelurahan = view.findViewById(R.id.spinnerKelurahan);
        TextInputEditText etDetailAlamat = view.findViewById(R.id.etDetailAlamat);
        tvAddress = view.findViewById(R.id.tvAddress);

        /*----------Provinsi---------*/
        List<Address> listProvinsi = utils.getAllProvinsi();
        List<String> list1Id = new ArrayList<>();
        List<String> list1Name = new ArrayList<>();
        for (int i = 0; i < listProvinsi.size(); i++) {
            list1Id.add(listProvinsi.get(i).getId());
            list1Name.add(listProvinsi.get(i).getNama());
        }

        spinnerProvinsi.setAdapter(new ArrayAdapter<>(application, android.R.layout.simple_spinner_dropdown_item, list1Name));

        /*----------Action-----------*/
        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                utils.setAddressProvinsi(new Address(list1Id.get(position), list1Name.get(position)));

                /*------------Kota-----------*/
                List<Address> listKota = utils.getAllKota();
                List<String> list2Id = new ArrayList<>();
                List<String> list2Name = new ArrayList<>();
                for (int i = 0; i < listKota.size(); i++) {
                    list2Id.add(listKota.get(i).getId());
                    list2Name.add(listKota.get(i).getNama());
                }
                spinnerKota.setAdapter(new ArrayAdapter<>(application, android.R.layout.simple_spinner_dropdown_item, list2Name));
                spinnerKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        utils.setAddressKota(new Address(list2Id.get(position), list2Name.get(position)));

                        /*---------Kecamatan---------*/
                        List<Address> listKecamatan = utils.getAllKecamatan();
                        List<String> list3Id = new ArrayList<>();
                        List<String> list3Name = new ArrayList<>();
                        for (int i = 0; i < listKecamatan.size(); i++) {
                            list3Id.add(listKecamatan.get(i).getId());
                            list3Name.add(listKecamatan.get(i).getNama());
                        }
                        spinnerKecamatan.setAdapter(new ArrayAdapter<>(application, android.R.layout.simple_spinner_dropdown_item, list3Name));
                        spinnerKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                utils.setAddressKecamatan(new Address(list3Id.get(position), list3Name.get(position)));

                                /*---------Kelurahan---------*/
                                List<Address> listKelurahan = utils.getAllKelurahan();
                                List<String> list4Id = new ArrayList<>();
                                List<String> list4Name = new ArrayList<>();
                                for (int i = 0; i < listKelurahan.size(); i++) {
                                    list4Id.add(listKelurahan.get(i).getId());
                                    list4Name.add(listKelurahan.get(i).getNama());
                                }
                                spinnerKelurahan.setAdapter(new ArrayAdapter<>(application, android.R.layout.simple_spinner_dropdown_item, list4Name));
                                spinnerKelurahan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        utils.setAddressKelurahan(new Address(list4Id.get(position), list4Name.get(position)));
                                        tvAddress.setText(utils.getAddress());
                                        showListener.onAddress(utils.getIdSellected(), utils.getAddress());
                                        showListener.onAddressComplete(
                                                utils.getAddressProvinsi(),
                                                utils.getAddressKota(),
                                                utils.getAddressKecamatan(),
                                                utils.getAddressKelurahan(),
                                                utils.getDetailAddress()
                                        );
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etDetailAlamat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                utils.setDetailAddress(s.toString());
                tvAddress.setText(utils.getAddress());
                showListener.onAddress(utils.getIdSellected(), utils.getAddress());
                showListener.onAddressComplete(
                        utils.getAddressProvinsi(),
                        utils.getAddressKota(),
                        utils.getAddressKecamatan(),
                        utils.getAddressKelurahan(),
                        utils.getDetailAddress()
                );

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    public interface OnShowListener {
        void onAddress(String id, String address);

        void onAddressComplete(Address provinsi, Address kota, Address kecamatan, Address kelurahan, String detail);
    }


}
