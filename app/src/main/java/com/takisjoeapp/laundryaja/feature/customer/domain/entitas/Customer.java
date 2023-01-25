package com.takisjoeapp.laundryaja.feature.customer.domain.entitas;

import java.security.Timestamp;
import java.util.List;

public class Customer {
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

    public Customer() {
    }

    public Customer(String id, String name, String username, String phone, String address, Long createdAt, List<String> orders) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.orders = orders;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }
}
