package com.takisjoeapp.laundryaja.feature.customer.data;

import android.content.Context;
import android.widget.Toast;

import com.takisjoeapp.laundryaja.feature.customer.data.database.FirebaseCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.PreferencesCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.RoomCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class CustomerDataImpl implements CustomerData{
    private FirebaseCustomerData firebaseCustomerData;
    private PreferencesCustomerData preferencesCustomerData;
    private RoomCustomerData roomCustomerData;
    private Context context; 

    private final List<Customer> customerList;

    public CustomerDataImpl() {
        customerList = new ArrayList<>();
        firebaseCustomerData = new FirebaseCustomerData();
        preferencesCustomerData = new PreferencesCustomerData();
        roomCustomerData = new RoomCustomerData();
        context = ServiceLocator.getService("addCustomer");
    }

    @Override
    public boolean create(Customer customer) {
        for (Customer cust: customerList) {
            if (cust.getId().equals(customer.getId())){
                return false;
            }
        }
        customerList.add(customer);

        //TODO: implementasi untuk menambahkan data customer ke dalam database dengan memastikan bahwa ID yang diinputkan tidak sama dengan ID yang sudah ada dalam database
        Toast.makeText(context, "Ditambahkan - "+customerList.size(), Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public boolean update(String idCustomer, Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(idCustomer)) {
                customerList.set(i, customer);
                //TODO: implementasi untuk memperbarui data customer di dalam database
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String idCustomer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(idCustomer)) {
                customerList.remove(i);
                //TODO: implementasi untuk menghapus data customer dari dalam database
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Customer> read() {
        //TODO: implementasi untuk mengambil seluruh data customer dari dalam database
        return customerList;
    }
}
