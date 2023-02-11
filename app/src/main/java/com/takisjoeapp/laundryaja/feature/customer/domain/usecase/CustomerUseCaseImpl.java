package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.takisjoeapp.laundryaja.feature.customer.data.CustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.OnCustomerDataListener;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.repository.CustomerRepository;
import com.takisjoeapp.laundryaja.feature.customer.domain.repository.CustomerRepositoryImpl;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.feature.user.domain.usecase.UserUsecase;
import com.takisjoeapp.laundryaja.feature.user.domain.usecase.UserUsecaseImpl;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;
import com.takisjoeapp.laundryaja.util.validation.PhoneNumberUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    private final UserUsecase userUsecase;

    public CustomerUseCaseImpl(ModeService modeService) {
        userUsecase = new UserUsecaseImpl();
        customerRepository = new CustomerRepositoryImpl(userUsecase.getUser(), modeService);
    }

    /*-------------------------------------------Tambah Customer------------------------------------*/
    @Override
    public void addCustomer(@NonNull String nama, String phone, String alamat, OnCustomerUseCaseListener.Create create) {
        //Default new customer
        if (nama.isEmpty()) {
            create.onFailure("Nama belum diisi");
            return;
        }
        if (phone.isEmpty()) {
            create.onFailure("Nomor belum diisi");
            return;
        }
        if (alamat.isEmpty()) {
            create.onFailure("Alamat belum diisi");
            return;
        }

        HashSet<String> suscribers = new HashSet<>();
        suscribers.add(userUsecase.getUser().getIdLaundry());

        Customer customer = new CustomerBuilder()
                .setName(nama)
                .setPhone(phone)
                .setAddress(alamat)
                .setNotification_ids(new ArrayList<>(suscribers))
                .setCreatedAt(TimeUtils.getTimestamp()) //Menyesuaikan waktu kapan customer dibuat
                .setUpdatedAt(TimeUtils.getTimestamp()) //Menyesuaikan waktu kapan customer diupdate
                .setStatus_user(true) //Secara default customer langsung aktif
                .setUsername(nama.replace(" ", "") + "@laundryaja.c")
                .build();

        customerRepository.create(customer, new OnCustomerDataListener.Create() {
            @Override
            public void onSuccess(Customer customer) {
                create.onSuccess(customer);
            }

            @Override
            public void onFailure(Exception e) {
                create.onFailure(e.getMessage());
            }
        });
    }

    /*--------------------------------------Mendapatkan Daftar Customer-----------------------------*/
    @Override
    public void showAllCustomer(LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.ReadCustomer onCustomerUseCaseListener) {
        ServiceLocator.registerService(CustomerData.DI_LifecycleOwner, lifecycleOwner);

        customerRepository.readAll(new OnCustomerDataListener.ReadAll() {
            @Override
            public void onSuccess(List<Customer> customerListList) {
                onCustomerUseCaseListener.onSuccess(customerListList);
            }

            @Override
            public void onFailure(String error) {
                onCustomerUseCaseListener.onFailure(error);
            }
        });

    }

    /*-----------------------------------------Pencarian Customer-----------------------------------*/
    private CountDownTimer downTimer;
    private boolean isConnection = true;
    private final List<Customer> filteredCustomers = new ArrayList<>();

    @Override
    public void searchCustomer(String search, LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.SearchCustomers searchCustomers) {
        String phone = PhoneNumberUtils.formatPhoneNumber(search);
        ServiceLocator.registerService(CustomerData.DI_LifecycleOwner, lifecycleOwner);
        filteredCustomers.clear();
        downTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                searchCustomers.onLoad(true, (int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isConnection = true;
                DebugLog.RULES(phone, "Ditemukan");

                customerRepository.searchByPhoneServer(phone, new OnCustomerDataListener.ReadAll() {
                    @Override
                    public void onSuccess(List<Customer> orderList) {
                        for (Customer customer : orderList) {
                            boolean isDuplicate = false;
                            for (Customer existingCustomer : filteredCustomers) {
                                if (Objects.equals(customer.getId(), existingCustomer.getId())) {
                                    isDuplicate = true;
                                    break;
                                }
                            }
                            if (!isDuplicate) {
                                filteredCustomers.add(customer);
                            }
                        }
                        searchCustomers.onLoad(false, 0);
                        searchCustomers.onCustomersResult(filteredCustomers);
                    }

                    @Override
                    public void onFailure(String error) {

                    }
                });
                DebugLog.DEBUG("Mencari ke server", "CustomerUseCase");
            }
        };

        customerRepository.readAll(new OnCustomerDataListener.ReadAll() {
            @Override
            public void onSuccess(List<Customer> orderList) {
                for (Customer customer : orderList) {
                    if (customer.getName().toUpperCase(Locale.ROOT).contains(search.toUpperCase(Locale.ROOT)) || customer.getPhone().contains(PhoneNumberUtils.formatPhoneNumber(search))) {
                        filteredCustomers.add(customer);
                    }
                }

                searchCustomers.onCustomersResult(filteredCustomers);

                updateFilteredCustomers(search);
            }

            @Override
            public void onFailure(String error) {
                searchCustomers.onFailure(error);
            }
        });
    }
    private final int DELAY_IN_MILLISECONDS = 3000;
    private Timer timer;
    private boolean isTimerRunning = false;

    private void updateFilteredCustomers(String search) {
        if (!filteredCustomers.isEmpty()) {
            if (isTimerRunning) {
                timer.cancel();
                isTimerRunning = false;
            }
        } else if (!search.isEmpty()) {
            if (!isTimerRunning) {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        // Tindakan yang diambil setelah 3 detik delay
                        downTimer.start();
                    }
                }, DELAY_IN_MILLISECONDS);
                isTimerRunning = true;
            }
        }
    }


    @Override
    public void searchCustomerLocal(String search, LifecycleOwner lifecycleOwner, OnCustomerUseCaseListener.SearchLocalCustomer searchLocalCustomer) {
        ServiceLocator.registerService(CustomerData.DI_Application, lifecycleOwner);

        customerRepository.readAll(new OnCustomerDataListener.ReadAll() {
            @Override
            public void onSuccess(List<Customer> customerList) {
                List<Customer> filteredCustomers = new ArrayList<>();
                for (Customer customer : customerList) {
                    if (customer.getName().toUpperCase(Locale.ROOT).contains(search.toUpperCase(Locale.ROOT)) || customer.getPhone().contains(PhoneNumberUtils.formatPhoneNumber(search))) {
                        filteredCustomers.add(customer);
                    }
                }
                searchLocalCustomer.onSuccess(filteredCustomers);
            }

            @Override
            public void onFailure(String error) {
                searchLocalCustomer.onFailure(error);
            }
        });
    }
}
