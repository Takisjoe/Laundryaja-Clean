package com.takisjoeapp.laundryaja.feature.customer.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.data.CustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.CustomerDataImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public class CustomerRepository {
    private final CustomerData customerData;

    public CustomerRepository() {
        customerData = new CustomerDataImpl();
    }

    /**
     * Tambahkan customer baru ke dalam database
     *
     * @param customer objek customer yang akan ditambahkan
     */
    public boolean addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer tidak boleh kosong");
        } else {
            //TODO: implementasi menambahkan customer ke dalam database
            return customerData.create(customer);
        }
    }


    /**
     * Ambil customer berdasarkan ID
     *
     * @param id ID customer yang ingin diambil
     * @return customer yang ditemukan
     */
    public Customer getCustomerById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID customer tidak boleh kosong");
        } else {
            //TODO: implementasi untuk mengambil customer berdasarkan ID dari database
            for (Customer customer : customerData.read()) {
                if (customer.getId().equals(id)) {
                    return customer;
                }
            }
            return null;
        }
    }


    /**
     * Update informasi customer
     *
     * @param customer objek customer yang akan diperbarui
     */
    public boolean updateCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer tidak boleh kosong");
        } else {
            //TODO: implementasi untuk memperbarui customer di dalam database
            return customerData.update(customer.getId(), customer);
        }
    }


    /**
     * Hapus customer berdasarkan ID
     *
     * @param id ID customer yang ingin dihapus
     */
    public boolean deleteCustomer(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID customer tidak boleh kosong");
        } else {
            //TODO: implementasi untuk menghapus customer dari database
            return customerData.delete(id);
        }
    }


    /**
     * Mengambil semua customer dalam sistem
     *
     * @return List of customer
     */
    public List<Customer> getAll() {
        //TODO: implementasi untuk mengambil semua customer dari database
        return customerData.read();
    }

}

