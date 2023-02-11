package com.takisjoeapp.laundryaja.feature.customer.data.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VirtualCustomerData extends ViewModel {
    private final List<Customer> customerList;

    public VirtualCustomerData() {
        customerList = new ArrayList<>();
    }

    public void create(Customer customer) {
        customerList.add(customer);
        System.out.println("Menambah customer " + customer.getName());
    }

    public List<Customer> read() {
        System.out.println("Mengambil data customer - " + customerList.size());
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println((i + 1) + ". ID : " + customerList.get(i).getId() + " | Nama : " + customerList.get(i).getName());
        }
        return customerList;
    }

    public void update(Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().contains(customer.getId())) {
                customerList.set(i, customer);
                return;
            }
        }
        System.out.println("Customer " + customer.getId() + " Belum Terdaftar");
    }

    public void delete(Customer customer) {
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().contains(customer.getId())) {
                customerList.remove(i);
                return;
            }
        }
        System.out.println("Customer " + customer.getId() + " tidak ada dalam database");
    }

    public void clearAll() {
        customerList.clear();
    }

    public static Customer fakeCustomer() {
        int num = new Random().nextInt();
        return new CustomerBuilder().setId(generateCustomerFake()).setName("Nama Pelanggan ke-" + num).build();
    }

    private static int number = 0;

    @NonNull
    private static String generateCustomerFake() {
        number++;
        return "id-" + number;
    }
}
