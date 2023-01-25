package com.takisjoeapp.laundryaja.feature.customer.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.takisjoeapp.laundryaja.feature.customer.data.database.FirestoreCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.PreferencesCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.RoomCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataImpl implements CustomerData {
    private FirestoreCustomerData firestoreCustomerData;
    private PreferencesCustomerData preferencesCustomerData;
    private RoomCustomerData roomCustomerData;
    private Context context;

    private final List<Customer> customerList;

    public CustomerDataImpl() {
        customerList = new ArrayList<>();
        firestoreCustomerData = new FirestoreCustomerData();
        preferencesCustomerData = new PreferencesCustomerData();
        roomCustomerData = new RoomCustomerData();
        context = ServiceLocator.getService("addCustomer");
        context = ServiceLocator.getService("mainCustomer");
    }

    @Override
    public boolean create(Customer customer) {
        for (Customer cust : customerList) {
            if (cust.getId().equals(customer.getId())) {
                return false;
            }
        }

        //TODO: implementasi untuk menambahkan data customer ke dalam database dengan memastikan bahwa ID yang diinputkan tidak sama dengan ID yang sudah ada dalam database
        firestoreCustomerData.addCustomer(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                customerList.add(customer);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
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
    public LiveData<List<Customer>> read() {
//        customerList.clear();
        MutableLiveData liveData = new MutableLiveData(customerList);

        firestoreCustomerData.getAllCustomer().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                customerList.clear();
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Customer customer = documentSnapshot.toObject(Customer.class);
                    System.out.println(customer.getName());
                    customerList.add(customer);
                }
                liveData.setValue(customerList);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Firebase Error", e.getMessage());
            }
        });
        System.out.print("Memanggil read (CustomerDataImpl) {"+customerList+"} -> ");

        return liveData;
    }
}
