package com.takisjoeapp.laundryaja.feature.payment.domain.entitas;

public class Payment {
    // ID unik dari pembayaran
    private String id;
    // ID dari order yang dibayar
    private String orderId;
    // ID dari customer yang melakukan pembayaran
    private String customerId;
    // Jumlah yang dibayar
    private Double amount;
    // Metode pembayaran (credit card, debit card, transfer bank, dll)
    private String paymentMethod;
    // Waktu pembayaran dilakukan
    private Long paymentTime;
    // Status pembayaran (success, failed, pending)
    private String status;
}
