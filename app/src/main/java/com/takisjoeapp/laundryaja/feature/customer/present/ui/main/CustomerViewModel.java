package com.takisjoeapp.laundryaja.feature.customer.present.ui.main;

import android.os.CountDownTimer;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUsecaseImpl;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import java.util.List;

public class CustomerViewModel extends ViewModel {
    private final CustomerUseCase customerUseCase;
    private MutableLiveData<List<Customer>> liveData = new MutableLiveData<>();

    public CustomerViewModel() {
        customerUseCase = new CustomerUsecaseImpl();
    }

    // TODO: Implement the ViewModel
    public LiveData<List<Customer>> getAllCustomers() {
//        fakeCustomer();

        return customerUseCase.getAllCustomers(new CustomerUseCase.OnGetAllCustomersCallback() {
            @Override
            public void onSuccess(List<Customer> customers) {
                liveData.setValue(customers);
                System.out.print("Memanggil getAllCustomers (CustomerViewModel) {"+customers.size()+"} -> ");

            }

            @Override
            public void onError(String message) {

            }
        });
    }


    private void fakeCustomer() {
        for (int i = 0; i < 10; i++) {
            Customer customer = new CustomerBuilder().setId("id-" + i).setName("Pengguna-" + i).setPhone("phone-" + i).setAddress("alamat-" + i).setUsername("username-" + i).build();

            customerUseCase.addCustomer(customer, new CustomerUseCase.OnAddCustomerCallback() {
                @Override
                public void onSuccess(boolean isSuccess) {
                    System.out.println("Berhasil menambah customer");
                }

                @Override
                public void onFailed(String message) {

                }
            });
        }
        new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                System.out.println(millisUntilFinished / 1000 + " detik");
            }

            @Override
            public void onFinish() {
                for (int i = 0; i < 10; i++) {
                    Customer customer = new CustomerBuilder().setId("idA-" + i).setName("Pengguna-" + i).setPhone("phone-" + i).setAddress("alamat-" + i).setUsername("username-" + i).build();

                    customerUseCase.addCustomer(customer, new CustomerUseCase.OnAddCustomerCallback() {
                        @Override
                        public void onSuccess(boolean isSuccess) {
                            System.out.println("Berhasil menambah customer lagi");
                        }

                        @Override
                        public void onFailed(String message) {
                            System.out.println(message);

                        }
                    });
                }
                customerUseCase.getAllCustomers(new CustomerUseCase.OnGetAllCustomersCallback() {
                    @Override
                    public void onSuccess(List<Customer> customers) {
                        liveData.setValue(customers);
                    }

                    @Override
                    public void onError(String message) {

                    }
                });
            }
        }.start();
    }
}