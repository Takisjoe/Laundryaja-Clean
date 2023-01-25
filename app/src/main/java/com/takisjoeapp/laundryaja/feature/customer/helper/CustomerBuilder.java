package com.takisjoeapp.laundryaja.feature.customer.helper;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.List;

public class CustomerBuilder {
    // ID unik dari customer
    private String id;
    // Nama lengkap dari customer
    private String name;
    // Nama unik yang digunakan sebagai email customer (username@laundryaja.com)
    private String username;
    // Nomor telepon customer yang digunakan untuk verifikasi OTP dan komunikasi
    private String phone;
    // Alamat customer
    private String address;
    //Tanggal dan waktu customer ditambahkan ke dalam sistem
    private Long createdAt;
    // Daftar ID dari order yang dipesan oleh customer
    private List<String> orders;

    public Customer build() {
        return new Customer(id, name, username, phone, address, createdAt, orders);
    }

    public CustomerBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public CustomerBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CustomerBuilder setOrders(List<String> orders) {
        this.orders = orders;
        return this;
    }
}
