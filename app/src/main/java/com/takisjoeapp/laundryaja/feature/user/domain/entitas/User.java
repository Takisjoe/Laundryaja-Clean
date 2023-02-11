package com.takisjoeapp.laundryaja.feature.user.domain.entitas;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.util.List;

public class User {
    @PrimaryKey
    @NonNull
    private String id;// ID unik dari user

    @ColumnInfo(name = "name_user")
    private String name;// Nama lengkap dari user

    @ColumnInfo(name = "username_user")
    private String username;// Nama unik yang digunakan sebagai email user (username@laundryaja.com)

    @ColumnInfo(name = "phone_user")
    private String phone;// Nomor telepon user yang digunakan untuk verifikasi OTP dan komunikasi

    @ColumnInfo(name = "address_user")
    private String address;// Alamat user

    @ColumnInfo(name = "email_user")
    private String email; // email user

    @ColumnInfo(name = "password_user")
    private String password; // password user

    @ColumnInfo(name = "idLaundry_user")
    private String idLaundry; // id laundry yang dimiliki oleh user (jika user adalah bos laundry)

    @ColumnInfo(name = "idBranch_user")
    private String idBranch; // id cabang laundry yang dikelola oleh user (jika user adalah karyawan)

    @ColumnInfo(name = "role_user")
    private String role; // peran user (user atau karyawan)

    @ColumnInfo(name = "uid_user")
    private String uid; // id user dari Firebase Firestore

    @ColumnInfo(name = "photo_user")
    private String photo; // url photo profil user

    @ColumnInfo(name = "status_user")
    private String status; // status user (aktif atau tidak aktif)

    @ColumnInfo(name = "registrationToken_user")
    private String registrationToken; // token registration untuk push notification

    @ColumnInfo(name = "whatsapp_verified_user")
    private boolean whatsapp_verified; //status verifikasi email

    @ColumnInfo(name = "verified_user")
    private boolean verified; // status verifikasi email

    @ColumnInfo(name = "isAdmin_user")
    private boolean isAdmin; // status apakah user adalah admin atau bukan

    @ColumnInfo(name = "createAt_user")
    private Long createdAt;//Tanggal dan waktu user ditambahkan ke dalam sistem

    @ColumnInfo(name = "updateAt_user")
    private Long updatedAt; // waktu update user

    @ColumnInfo(name = "status_user_user")
    private boolean status_user; // status user (aktif atau nonaktif)

    @ColumnInfo(name = "notification_ids_user")
    private List<String> notification_ids; // list id notification yang diterima user

    @ColumnInfo(name = "order_ids_user")
    private List<String> order_ids; // list id order yang dibuat user

    @ColumnInfo(name = "payment_user")
    private List<String> payment_ids; // list id pembayaran yang dilakukan user

    @ColumnInfo(name = "laundry_ids_user")
    private List<String> laundry_ids; // list id laundry yang dimiliki user (jika user adalah bos laundry)

    @ColumnInfo(name = "service_ids_user")
    private List<String> service_ids; // list id service yang dipesan user

    @ColumnInfo(name = "employee_ids_user")
    private List<String> employee_ids; // list id karyawan yang dikelola user (jika user adalah karyawan)

    @ColumnInfo(name = "branch_ids_user")
    private List<String> branch_ids; // list id cabang laundry yang dikelola user (jika user adalah karyawan)

    public User() {
    }

    public User(@NonNull String id, String name, String username, String phone, String address, String email, String password, String idLaundry, String idBranch, String role, String uid, String photo, String status, String registrationToken, boolean whatsapp_verified, boolean verified, boolean isAdmin, Long createdAt, Long updatedAt, boolean status_user, List<String> notification_ids, List<String> order_ids, List<String> payment_ids, List<String> laundry_ids, List<String> service_ids, List<String> employee_ids, List<String> branch_ids) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.password = password;
        this.idLaundry = idLaundry;
        this.idBranch = idBranch;
        this.role = role;
        this.uid = uid;
        this.photo = photo;
        this.status = status;
        this.registrationToken = registrationToken;
        this.whatsapp_verified = whatsapp_verified;
        this.verified = verified;
        this.isAdmin = isAdmin;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status_user = status_user;
        this.notification_ids = notification_ids;
        this.order_ids = order_ids;
        this.payment_ids = payment_ids;
        this.laundry_ids = laundry_ids;
        this.service_ids = service_ids;
        this.employee_ids = employee_ids;
        this.branch_ids = branch_ids;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdLaundry() {
        return idLaundry;
    }

    public void setIdLaundry(String idLaundry) {
        this.idLaundry = idLaundry;
    }

    public String getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(String idBranch) {
        this.idBranch = idBranch;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public boolean isWhatsapp_verified() {
        return whatsapp_verified;
    }

    public void setWhatsapp_verified(boolean whatsapp_verified) {
        this.whatsapp_verified = whatsapp_verified;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus_user() {
        return status_user;
    }

    public void setStatus_user(boolean status_user) {
        this.status_user = status_user;
    }

    public List<String> getNotification_ids() {
        return notification_ids;
    }

    public void setNotification_ids(List<String> notification_ids) {
        this.notification_ids = notification_ids;
    }

    public List<String> getOrder_ids() {
        return order_ids;
    }

    public void setOrder_ids(List<String> order_ids) {
        this.order_ids = order_ids;
    }

    public List<String> getPayment_ids() {
        return payment_ids;
    }

    public void setPayment_ids(List<String> payment_ids) {
        this.payment_ids = payment_ids;
    }

    public List<String> getLaundry_ids() {
        return laundry_ids;
    }

    public void setLaundry_ids(List<String> laundry_ids) {
        this.laundry_ids = laundry_ids;
    }

    public List<String> getService_ids() {
        return service_ids;
    }

    public void setService_ids(List<String> service_ids) {
        this.service_ids = service_ids;
    }

    public List<String> getEmployee_ids() {
        return employee_ids;
    }

    public void setEmployee_ids(List<String> employee_ids) {
        this.employee_ids = employee_ids;
    }

    public List<String> getBranch_ids() {
        return branch_ids;
    }

    public void setBranch_ids(List<String> branch_ids) {
        this.branch_ids = branch_ids;
    }
}
