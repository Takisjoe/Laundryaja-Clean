package com.takisjoeapp.laundryaja.feature.order.domain.usecase;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCase;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.CustomerUseCaseImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.usecase.OnCustomerUseCaseListener;
import com.takisjoeapp.laundryaja.feature.order.adapter.DrafCustomerOrderAdapter;
import com.takisjoeapp.laundryaja.feature.order.adapter.OnDrafCustomerOrderListener;
import com.takisjoeapp.laundryaja.feature.order.data.OnOrderDataListener;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.domain.repository.OrderRepository;
import com.takisjoeapp.laundryaja.feature.order.domain.repository.OrderRepositoryImpl;
import com.takisjoeapp.laundryaja.feature.user.domain.usecase.UserUsecase;
import com.takisjoeapp.laundryaja.feature.user.domain.usecase.UserUsecaseImpl;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.List;

/**
 * Kelas OrdersUseCaseImpl mengimplementasikan interface OrdersUseCase.
 * Kelas ini memiliki tiga dependensi yaitu OrderRepository, UserUsecase, dan CustomerUseCase.
 *
 * @author Celvinanda Octiawan
 * @version 1.0
 */
public class OrdersUseCaseImpl implements OrdersUseCase {

    /**
     * Repository yang digunakan untuk mengakses data pesanan.
     */
    private final OrderRepository orderRepository;

    /**
     * Usecase yang berhubungan dengan entitas user.
     */
    private final UserUsecase userUsecase;

    /**
     * Usecase yang berhubungan dengan entitas customer.
     */
    private final CustomerUseCase customerUseCase;

    /**
     * Konstruktor kelas OrdersUseCaseImpl.
     *
     * @param modeService merupakan objek modeService yang digunakan untuk melakukan operasi pada data.
     */
    public OrdersUseCaseImpl(ModeService modeService) {
        //Inisialisasi dependensi
        userUsecase = new UserUsecaseImpl();
        customerUseCase = new CustomerUseCaseImpl(modeService);
        orderRepository = new OrderRepositoryImpl(userUsecase.getUser(), modeService);
    }

    /**
     * Method untuk membuat order baru
     *
     * @param customer    instance dari kelas {@link Customer} yang memiliki informasi customer
     * @param order       instance dari kelas {@link Order} yang memiliki informasi order
     * @param createOrder instance dari interface {@link OnOrderUseCaseListener.CreateOrder} untuk memberikan response
     *                    setelah proses pembuatan order selesai
     */
    @Override
    public void createOrder(@NonNull Customer customer, @NonNull Order order, OnOrderUseCaseListener.CreateOrder createOrder) {
        // Set employee ID dari user yang sedang login
        order.setEmployyeId(userUsecase.getUser().getId());
        // Set customer ID dari customer yang melakukan order
        order.setCustomerId(customer.getId());
        // Set laundry ID dari user yang sedang login
        order.setLaundryId(userUsecase.getUser().getIdLaundry());
        // Set branch ID dari user yang sedang login
        order.setBranchId(userUsecase.getUser().getIdBranch());
        // Set status order menjadi "Pick-up"
        order.setStatus("Pick-up");

        // Memanggil method create dari order repository
        orderRepository.create(order, new OnOrderDataListener.CreateOrder() {
            @Override
            public void onSuccess() {
                // Memberikan response success melalui createOrder
                createOrder.onSuccess(order);
            }

            @Override
            public void onFailure(String error) {
                // Memberikan response error melalui createOrder
                createOrder.onFailure(error);
            }
        });
    }

    @Override
    public void drafCustomer(String search, LifecycleOwner lifecycleOwner, OnOrderUseCaseListener.DrafCustomerOrder customerOrder) {
        customerUseCase.searchCustomer(search, lifecycleOwner, new OnCustomerUseCaseListener.SearchCustomers() {
            @Override
            public void onLoad(boolean isLoad, int detik) {
                customerOrder.onLoad(isLoad, detik);
            }

            @Override
            public void onCustomersResult(List<Customer> customerList) {
                DrafCustomerOrderAdapter adapter = new DrafCustomerOrderAdapter(customerList);
                customerOrder.onSelected(null, false, adapter.getItemCount());
                adapter.setListener(new OnDrafCustomerOrderListener() {
                    @Override
                    public void onItemClick(View view, Customer customer) {
//                        orderRepository.drafCustomer(customer);
                        customerOrder.onSelected(customer, true, adapter.getItemCount());
                    }
                });

                if (search.isEmpty()) {
                    customerOrder.onSelected(null, false, adapter.getItemCount());
                } else {
                    customerOrder.onSuccess(adapter);
                }
            }

            @Override
            public void onFailure(String Error) {
                customerOrder.onFailure(Error);
            }
        });

//        customerUseCase.searchCustomerLocal(search, lifecycleOwner, new OnCustomerUseCaseListener.SearchLocalCustomer() {
//            @Override
//            public void onSuccess(List<Customer> customerList) {
//                DrafCustomerOrderAdapter adapter = new DrafCustomerOrderAdapter(customerList);
//
//                customerOrder.onSelected(null, false, adapter.getItemCount());
//                adapter.setListener(new OnDrafCustomerOrderListener() {
//                    @Override
//                    public void onItemClick(View view, Customer customer) {
////                        orderRepository.drafCustomer(customer);
//                        customerOrder.onSelected(customer, true, adapter.getItemCount());
//                    }
//                });
//
//                if (search.isEmpty()) {
//                    customerOrder.onSelected(null, false, adapter.getItemCount());
//                } else {
//                    customerOrder.onSuccess(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(String Error) {
//                customerOrder.onFailure(Error);
//            }
//        });
    }

    @Override
    public void drafCustomer(Customer customer, OnOrderUseCaseListener.DrafNewCustomerOrder newCustomerOrder) {
        customer.setId("NewCustomer");
        if (customer.getName().isEmpty()){
            newCustomerOrder.onFailure("Nama harus diisi");
            return;
        }
        if (customer.getPhone().isEmpty()){
            newCustomerOrder.onFailure("Whatsapp harus diisi");
            return;
        }
        if (customer.getAddress().isEmpty()){
            newCustomerOrder.onFailure("Alamat harus diisi");
            return;
        }
        orderRepository.drafCustomer(customer);
        newCustomerOrder.onSuccess(customer);
    }

    @Override
    public void drafOrder(Order order) {
        order.setEmployyeId(userUsecase.getUser().getId());
        order.setLaundryId(userUsecase.getUser().getIdLaundry());
        order.setBranchId(userUsecase.getUser().getIdBranch());
        order.setOrderDate(TimeUtils.getTimestamp());
        order.setStatus("Pick-Up");
        orderRepository.drafOrder(order);
    }

    /**
     * Method showAllOrder akan menampilkan semua order.
     *
     * @param lifecycleOwner instance dari kelas {@link LifecycleOwner} yang akan digunakan untuk memantau lifecycle dari observer
     * @param readOrder      instance dari interface {@link OnOrderUseCaseListener.ReadOrder} untuk memberikan response
     *                       setelah proses pembacaan order selesai
     */
    @Override
    public void showAllOrder(LifecycleOwner lifecycleOwner, OnOrderUseCaseListener.ReadOrder readOrder) {
        ServiceLocator.registerService("LifecycleOwnerOrderData", lifecycleOwner);

        orderRepository.readAll(new OnOrderDataListener.ReadAll() {
            @Override
            public void onSuccess(List<Order> orderList) {
                readOrder.onSuccess(orderList);
            }

            @Override
            public void onFailure(String error) {
                readOrder.onFailure(error);
            }
        });
    }


}
