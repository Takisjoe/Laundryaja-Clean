package com.takisjoeapp.laundryaja.feature.order.domain.entitas;

import java.util.Date;
import java.util.List;

public class Order {
    //ID order, Primary Key
    private String id;

    //ID customer yang memesan order
    private String customerId;

    //ID laundry yang menangani order
    private String laundryId;

    //ID branch yang menangani order
    private String branchId;

    //List of ID services yang dipesan
    private List<String> serviceIds;

    //Tanggal order dibuat
    private Date orderDate;

    //Tanggal order selesai
    private Date completionDate;

    //Total harga order
    private double totalPrice;

    //Admin fee 3% dari total harga order
    private double adminFee;

    //Pembayaran yang digunakan (QRIS/Tunai)
    private String paymentMethod;

    //Status order (pending, ongoing, completed)
    private String status;
}
