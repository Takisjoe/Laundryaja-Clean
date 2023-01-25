package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.repository.CustomerRepository;
import com.takisjoeapp.laundryaja.feature.payment.domain.entitas.Payment;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.List;

public class CustomerUsecaseImpl implements CustomerUseCase {
    private final CustomerRepository customerRepository;

    public CustomerUsecaseImpl() {
        customerRepository = new CustomerRepository();
    }

    @Override
    public void addCustomer(@NonNull Customer customer, OnAddCustomerCallback callback) {
        //Default
//        customer.setId("TEMPORARY-ID"); //Proses pembuatan Id ketika 'TEMPORARY-ID' maka ini akan digenerate oleh firebase
        customer.setCreatedAt(TimeUtils.getTimestamp()); //Menyesuaikan waktu kapan customer dibuat

        boolean isExist = customerRepository.getCustomerById(customer.getId()) != null;
        if (!isExist) {
            boolean isSuccess = customerRepository.addCustomer(customer);
            if (isSuccess) {
                callback.onSuccess(true);
            } else {
                callback.onFailed("Gagal menambahkan Customer");
            }
        } else {
            callback.onFailed("Customer sudah ada");
        }
    }

    @Override
    public void getCustomerById(String idCustomer, OnGetCustomerCallback callback) {
        Customer customer = customerRepository.getCustomerById(idCustomer);
        if (customer != null) {
            callback.onSuccess(customer);
        } else {
            callback.onFailed("Pelanggan tidak ditemukan");
        }
    }

    @Override
    public void updateCustomer(@NonNull Customer customer, OnUpdateCustomerCallback callback) {
        Customer origin = customerRepository.getCustomerById(customer.getId());
        if (customerRepository.getCustomerById(customer.getId()) == null) {
            callback.onFailed("Customer tidak ditemukan");
        } else {
            boolean isSuccess = customerRepository.updateCustomer(customer);
            if (isSuccess) {
                callback.onSuccess(true, origin, customer);
            } else {
                callback.onFailed("Gagal mengupdate Customer");
            }
        }
    }

    @Override
    public void deleteCustomer(String id, OnDeleteCustomerCallback callback) {
        Customer origin = customerRepository.getCustomerById(id);
        if (customerRepository.getCustomerById(id) == null) {
            callback.onFailed("Customer tidak ditemukan");
        } else {
            boolean isSuccess = customerRepository.deleteCustomer(id);
            if (isSuccess) {
                callback.onSuccess(true, origin);
            } else {
                callback.onFailed("Gagal menghapus Customer");
            }
        }
    }

    @Override
    public LiveData<List<Customer>> getAllCustomers(OnGetAllCustomersCallback callback) {
        LiveData<List<Customer>> customerList = customerRepository.getAll();
        if (customerList.getValue().size() == 0) {
            callback.onError("Tidak ada daftar customer");
        } else {
            callback.onSuccess(customerList.getValue());
        }
        System.out.print("Memanggil getAllCustomers (CustomerUsecaseImpl) {"+customerList.getValue().size()+"} -> ");

        return customerList;
    }

    @Override
    public void searchCustomers(String keyword, OnSearchCustomersCallback callback) {

//        List<Customer> customers = customerRepository.getCustomerById(keyword);
//        if (customers != null && !customers.isEmpty()) {
//            callback.onSuccess(customers);
//        } else {
//            callback.onError("Tidak ada customer yang ditemukan");
//        }

    }


    @Override
    public void verifyPhoneNumber(String customerId, String otp, OnVerifyPhoneNumberCallback callback) {

    }

    @Override
    public void getOrderHistory(String customerId, OnGetOrderHistoryCallback callback) {

    }

    @Override
    public void getCustomerOrderHistory(String customerId, OnGetCustomerOrderHistoryCallback callback) {

    }

    @Override
    public void makePayment(String orderId, Payment payment, OnMakePaymentCallback callback) {

    }

    @Override
    public void getOrderStatus(String orderId, OnGetOrderStatusCallback callback) {

    }


}
