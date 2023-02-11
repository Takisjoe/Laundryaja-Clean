package com.takisjoeapp.laundryaja.feature.customer.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.data.CustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.CustomerDataImpl;
import com.takisjoeapp.laundryaja.feature.customer.data.OnCustomerDataListener;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerData customerData;
    private final boolean isAccess;

    public CustomerRepositoryImpl(User user, ModeService modeService) {
        customerData = new CustomerDataImpl(user, modeService);
        if (modeService != ModeService.DEBUG) {
            isAccess = user.isAdmin();
        } else {
            isAccess = false;
        }
    }

    /**
     * Method ini digunakan untuk membuat baru data customer.
     *
     * @param customer untuk memberikan data yang akan dimasukan kedalam data
     * @param create   listener untuk mengetahui hasil dari proses pengambilan data
     */
    @Override
    public void create(Customer customer, OnCustomerDataListener.Create create) {
        if (isAccess) {
            customerData.create(customer, create);
        } else {
            create.onFailure(new Exception("Akses ditolak ,Hanya admin laundry yang dapat membuat customer"));
        }
    }

    @Override
    public void update(Customer customer, OnCustomerDataListener.Update update) {
        customerData.update(customer, update);
    }

    @Override
    public void delete(Customer customer, OnCustomerDataListener.Delete delete) {
        if (isAccess) {
            customerData.delete(customer, delete);
        } else {
            delete.onFailure(new Exception("Akses ditolak ,Hanya admin laundry yang dapat membuat customer"));
        }
    }

    /**
     * Method ini digunakan untuk mendapatkan daftar data customer.
     *
     * @param readAll listener untuk mengetahui hasil dari proses pengambilan data
     */
    @Override
    public void readAll(OnCustomerDataListener.ReadAll readAll) {
        customerData.getData(readAll);
    }

    @Override
    public void searchByPhoneServer(String search, OnCustomerDataListener.ReadAll readAll) {
        customerData.search(search, new OnCustomerDataListener.Search() {
            @Override
            public void onSuccess(List<Customer> customers) {
                readAll.onSuccess(customers);
            }

            @Override
            public void onFailure(Exception e) {
                readAll.onFailure(e.getMessage());
            }
        });
    }

    @Override
    public void drafCustomer(Customer customer) {

    }

    @Override
    public void loadDraf(OnCustomerDataListener.Draf draf) {

    }


    /**/
    /**
     * Tambahkan customer baru ke dalam database
     *
     * @param customer objek customer yang akan ditambahkan
     */
//    public boolean addCustomer(Customer customer, FirestoreCustomerData.OnAddCustomerListener listener) {
//        if (customer == null) {
//            return false;
//        } else {
//            //TODO: implementasi menambahkan customer ke dalam database
//            customerData.create(customer,listener);
//            return true;
//        }
//    }
//
//    public LiveData<List<Customer>> readMyCustomer() {
//        return customerData.read();
//    }
//
//
//    public void searchByPhone(String phone, FirestoreCustomerData.OnSearchListener listener) {
//        customerData.searchByPhone(phone,listener);
//    }
}

