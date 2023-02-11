package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.FirestoreCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public interface CustomerUseCase {

    /**
     * Method untuk menambahkan customer baru ke database
     *
     * @param nama     nama customer yang akan ditambahkan
     * @param phone    nomor telepon customer yang akan ditambahkan
     * @param alamat   alamat customer yang akan ditambahkan
     * @param create callback yang akan dipanggil setelah proses tambah customer selesai
     */
    void addCustomer(@NonNull String nama, String phone, String alamat, OnCustomerUseCaseListener.Create create);

    void showAllCustomer(LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.ReadCustomer onCustomerUseCaseListener);

    void searchCustomer(String search, LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.SearchCustomers searchCustomers);

    void searchCustomerLocal(String search, LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.SearchLocalCustomer searchLocalCustomer);
}




