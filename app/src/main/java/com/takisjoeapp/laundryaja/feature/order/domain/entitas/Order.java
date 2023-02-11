package com.takisjoeapp.laundryaja.feature.order.domain.entitas;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.takisjoeapp.laundryaja.database.room.NoteTypeConverter;

import java.util.List;

@Entity(tableName = "order_table")
public class Order {
    //ID order, Primary Key
    @PrimaryKey
    @NonNull
    private String id;

    //ID customer yang memesan order
    @ColumnInfo(name = "customer_id_order")
    private String customerId;

    //ID employee yang memesan order
    @ColumnInfo(name = "employye_id_order")
    private String employyeId;

    //ID laundry yang menangani order
    @ColumnInfo(name = "laundry_id_order")
    private String laundryId;

    //ID branch yang menangani order
    @ColumnInfo(name = "branch_id_order")
    private String branchId;

    //List of ID services yang dipesan
    @ColumnInfo(name = "service_ids_order")
    private List<String> serviceIds;

    //Tanggal order dibuat
    @ColumnInfo(name = "order_date_order")
    private Long orderDate;

    //Tanggal order selesai
    @ColumnInfo(name = "completionDate_order")
    private Long completionDate;

    //Nota order
    @ColumnInfo(name = "note_order")
    @TypeConverters(NoteTypeConverter.class)
    private List<Note> note;

    //Nota order
    @ColumnInfo(name = "catatan_order")
    private String catatan;

    //Total harga order
    @ColumnInfo(name = "totalPrice_order")
    private double totalPrice;

    //Admin fee 3% dari total harga order
    @ColumnInfo(name = "admin_fee_order")
    private double adminFee;

    //Pembayaran yang digunakan (QRIS/Tunai)
    @ColumnInfo(name = "payment_method_order")
    private String paymentMethod;

    //Status order (pending, ongoing, completed)
    @ColumnInfo(name = "status_order")
    private String status;

    @Ignore
    public Order() {
    }

    public Order(@NonNull String id, String customerId, String employyeId, String laundryId, String branchId, List<String> serviceIds, Long orderDate, Long completionDate, List<Note> note, String catatan, double totalPrice, double adminFee, String paymentMethod, String status) {
        this.id = id;
        this.customerId = customerId;
        this.employyeId = employyeId;
        this.laundryId = laundryId;
        this.branchId = branchId;
        this.serviceIds = serviceIds;
        this.orderDate = orderDate;
        this.completionDate = completionDate;
        this.note = note;
        this.catatan = catatan;
        this.totalPrice = totalPrice;
        this.adminFee = adminFee;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployyeId() {
        return employyeId;
    }

    public void setEmployyeId(String employyeId) {
        this.employyeId = employyeId;
    }

    public String getLaundryId() {
        return laundryId;
    }

    public void setLaundryId(String laundryId) {
        this.laundryId = laundryId;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public List<String> getServiceIds() {
        return serviceIds;
    }

    public void setServiceIds(List<String> serviceIds) {
        this.serviceIds = serviceIds;
    }

    public Long getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Long orderDate) {
        this.orderDate = orderDate;
    }

    public Long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Long completionDate) {
        this.completionDate = completionDate;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(double adminFee) {
        this.adminFee = adminFee;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
