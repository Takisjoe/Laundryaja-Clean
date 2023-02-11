package com.takisjoeapp.laundryaja.feature.order.helper;

import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Note;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

public class OrderBuilder {
    private String id = null;
    private String customerId = null;
    private String employyeId = null;
    private String laundryId = null;
    private String branchId = null;
    private List<String> serviceIds;
    private Long orderDate;
    private Long completionDate;
    private List<Note> note;
    private String catatan;
    private double totalPrice;
    private double adminFee;
    private String paymentMethod;
    private String status;

    public OrderBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderBuilder setEmployyeId(String employyeId) {
        this.employyeId = employyeId;
        return this;
    }

    public OrderBuilder setLaundryId(String laundryId) {
        this.laundryId = laundryId;
        return this;
    }

    public OrderBuilder setBranchId(String branchId) {
        this.branchId = branchId;
        return this;
    }

    public OrderBuilder setCatatan(String catatan) {
        this.catatan = OrderBuilder.this.catatan;
        return this;
    }

    public OrderBuilder setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
        return this;
    }

    public OrderBuilder setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public OrderBuilder setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
        return this;
    }

    public OrderBuilder setNote(List<Note> note) {
        this.note = note;
        return this;
    }

    public OrderBuilder setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public OrderBuilder setAdminFee(double adminFee) {
        this.adminFee = adminFee;
        return this;
    }

    public OrderBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Order build() {
        return new Order(id, customerId, employyeId, laundryId, branchId, serviceIds, orderDate, completionDate, note, catatan, totalPrice, adminFee, paymentMethod, status);
    }
}

