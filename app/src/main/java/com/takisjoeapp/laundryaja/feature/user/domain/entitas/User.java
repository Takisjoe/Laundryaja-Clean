package com.takisjoeapp.laundryaja.feature.user.domain.entitas;

import java.util.List;

public class User {
    private String id; // id user dari Firebase Authentication
    private String name; // nama user
    private String email; // email user
    private String phone; // nomor telepon user
    private String password; // password user
    private String idLaundry; // id laundry yang dimiliki oleh user (jika user adalah bos laundry)
    private String idBranch; // id cabang laundry yang dikelola oleh user (jika user adalah karyawan)
    private String role; // peran user (customer atau karyawan)
    private String uid; // id user dari Firebase Firestore
    private String address; // alamat user
    private String photo; // url photo profil user
    private String status; // status user (aktif atau tidak aktif)
    private String registrationToken; // token registration untuk push notification
    private boolean whatsapp_verified; //status verifikasi email
    private boolean verified; // status verifikasi email
    private boolean isAdmin; // status apakah user adalah admin atau bukan
    private String createdAt; // waktu pembuatan user
    private String updatedAt; // waktu update user
    private boolean status_user; // status user (aktif atau nonaktif)
    private List<String> notification_ids; // list id notification yang diterima user
    private List<String> order_ids; // list id order yang dibuat user
    private List<String> payment_ids; // list id pembayaran yang dilakukan user
    private List<String> laundry_ids; // list id laundry yang dimiliki user (jika user adalah bos laundry)
    private List<String> service_ids; // list id service yang dipesan user
    private List<String> employee_ids; // list id karyawan yang dikelola user (jika user adalah karyawan)
    private List<String> branch_ids; // list id cabang laundry yang dikelola user (jika user adalah karyawan)


}
