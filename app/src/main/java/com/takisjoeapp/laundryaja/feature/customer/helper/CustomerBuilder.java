package com.takisjoeapp.laundryaja.feature.customer.helper;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.util.time.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerBuilder {
    private String id = "AutoGenerate";// ID unik dari customer
    private String name = "empty";// Nama lengkap dari customer
    private String username = "empty";// Nama unik yang digunakan sebagai email customer (username@laundryaja.com)
    private String phone = "empty";// Nomor telepon customer yang digunakan untuk verifikasi OTP dan komunikasi
    private String address = "empty";// Alamat customer
    private String email = "empty"; // email user
    private String password = null; // password user
    private String idLaundry = null; // id laundry yang dimiliki oleh user (jika user adalah bos laundry)
    private String idBranch = null; // id cabang laundry yang dikelola oleh user (jika user adalah karyawan)
    private String role = "CUSTOMER"; // peran user (customer atau karyawan)
    private String uid = null; // id user dari Firebase Firestore
    private String photo = null; // url photo profil user
    private String status = "GOOD"; // status user (baik, bermasalah, buruk)
    private String registrationToken = "empty"; // token registration untuk push notification
    private boolean whatsapp_verified = false; //status verifikasi email
    private boolean verified = false; // status verifikasi email
    private boolean isAdmin = false; // status apakah user adalah admin atau bukan
    private Long createdAt = TimeUtils.getTimestamp();//Tanggal dan waktu customer ditambahkan ke dalam sistem
    private Long updatedAt = TimeUtils.getTimestamp(); // waktu update user
    private boolean status_user = false; // status user (aktif atau nonaktif)
    private List<String> notification_ids = new ArrayList<>(); // list id notification yang diterima user
    private List<String> order_ids = new ArrayList<>(); // list id order yang dibuat user
    private List<String> payment_ids = new ArrayList<>(); // list id pembayaran yang dilakukan user
    private List<String> laundry_ids = new ArrayList<>(); // list id laundry yang dimiliki user (jika user adalah bos laundry)
    private List<String> service_ids = new ArrayList<>(); // list id service yang dipesan user
    private List<String> employee_ids = new ArrayList<>(); // list id karyawan yang dikelola user (jika user adalah karyawan)
    private List<String> branch_ids = new ArrayList<>(); // list id cabang laundry yang dikelola user (jika user adalah karyawan)


    public Customer build() {
        return new Customer(
                id,
                name,
                username,
                phone,
                address,
                email,
                password,
                idLaundry,
                idBranch,
                role,
                uid,
                photo,
                status,
                registrationToken,
                whatsapp_verified,
                verified,
                isAdmin,
                createdAt,
                updatedAt,
                status_user,
                notification_ids,
                order_ids,
                payment_ids,
                laundry_ids,
                service_ids,
                employee_ids,
                branch_ids
        );
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

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
        return this;
    }

    public CustomerBuilder setIdBranch(String idBranch) {
        this.idBranch = idBranch;
        return this;
    }

    public CustomerBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public CustomerBuilder setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public CustomerBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public CustomerBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public CustomerBuilder setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
        return this;
    }

    public CustomerBuilder setWhatsapp_verified(boolean whatsapp_verified) {
        this.whatsapp_verified = whatsapp_verified;
        return this;
    }

    public CustomerBuilder setVerified(boolean verified) {
        this.verified = verified;
        return this;
    }

    public CustomerBuilder setAdmin(boolean admin) {
        isAdmin = admin;
        return this;
    }

    public CustomerBuilder setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public CustomerBuilder setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public CustomerBuilder setStatus_user(boolean status_user) {
        this.status_user = status_user;
        return this;
    }

    public CustomerBuilder setNotification_ids(List<String> notification_ids) {
        this.notification_ids = notification_ids;
        return this;
    }

    public CustomerBuilder setOrder_ids(List<String> order_ids) {
        this.order_ids = order_ids;
        return this;
    }

    public CustomerBuilder setPayment_ids(List<String> payment_ids) {
        this.payment_ids = payment_ids;
        return this;
    }

    public CustomerBuilder setLaundry_ids(List<String> laundry_ids) {
        this.laundry_ids = laundry_ids;
        return this;
    }

    public CustomerBuilder setService_ids(List<String> service_ids) {
        this.service_ids = service_ids;
        return this;
    }

    public CustomerBuilder setEmployee_ids(List<String> employee_ids) {
        this.employee_ids = employee_ids;
        return this;
    }

    public CustomerBuilder setBranch_ids(List<String> branch_ids) {
        this.branch_ids = branch_ids;
        return this;
    }
}
