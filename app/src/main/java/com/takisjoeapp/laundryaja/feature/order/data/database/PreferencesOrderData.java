package com.takisjoeapp.laundryaja.feature.order.data.database;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;

import java.util.Arrays;
import java.util.List;

public class PreferencesOrderData {
    private String LOG = "PreferencesOrderData";
    private final Application application;

    public PreferencesOrderData(Application application) {
        this.application = application;
        DebugLog.RULES("Memanggil divisi PreferencesOrderData", LOG);
    }

    public void writeCustomer(@NonNull Customer customer) {
        SharedPreferences sharedPreferences = application.getSharedPreferences("customer_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id", customer.getId());
        editor.putString("name", customer.getName());
        editor.putString("username", customer.getUsername());
        editor.putString("phone", customer.getPhone());
        editor.putString("address", customer.getAddress());
        editor.putString("email", customer.getEmail());
        editor.putString("password", customer.getPassword());
        editor.putString("idLaundry", customer.getIdLaundry());
        editor.putString("idBranch", customer.getIdBranch());
        editor.putString("role", customer.getRole());
        editor.putString("uid", customer.getUid());
        editor.putString("photo", customer.getPhoto());
        editor.putString("status", customer.getStatus());
        editor.putString("registrationToken", customer.getRegistrationToken());
        editor.putBoolean("whatsapp_verified", customer.isWhatsapp_verified());
        editor.putBoolean("verified", customer.isVerified());
        editor.putBoolean("isAdmin", customer.isAdmin());
        editor.putLong("createdAt", customer.getCreatedAt());
        editor.putLong("updatedAt", customer.getUpdatedAt());
//        editor.putBoolean("status_user", customer.isStatus_user());

        editor.putString("notification_ids", TextUtils.join(",", customer.getNotification_ids()));
        editor.putString("order_ids", TextUtils.join(",", customer.getOrder_ids()));
        editor.putString("payment_ids", TextUtils.join(",", customer.getPayment_ids()));
        editor.putString("laundry_ids", TextUtils.join(",", customer.getLaundry_ids()));
        editor.putString("service_ids", TextUtils.join(",", customer.getService_ids()));
        editor.putString("employee_ids", TextUtils.join(",", customer.getEmployee_ids()));
        editor.putString("branch_ids", TextUtils.join(",", customer.getBranch_ids()));

        editor.apply();
    }

    public Customer readCustomer() {

        SharedPreferences sharedPreferences = application.getSharedPreferences("customer_data", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("name")) {
            String id = sharedPreferences.getString("id", "");
            String name = sharedPreferences.getString("name", "");
            String username = sharedPreferences.getString("username", "");
            String phone = sharedPreferences.getString("phone", "");
            String address = sharedPreferences.getString("address", "");
            String email = sharedPreferences.getString("email", "");
            String password = sharedPreferences.getString("password", "");
            String idLaundry = sharedPreferences.getString("idLaundry", "");
            String idBranch = sharedPreferences.getString("idBranch", "");
            String role = sharedPreferences.getString("role", "");
            String uid = sharedPreferences.getString("uid", "");
            String photo = sharedPreferences.getString("photo", "");
            String status = sharedPreferences.getString("status", "");
            String registrationToken = sharedPreferences.getString("registrationToken", "");
            boolean whatsappVerified = sharedPreferences.getBoolean("whatsapp_verified", false);
            boolean verified = sharedPreferences.getBoolean("verified", false);
            boolean isAdmin = sharedPreferences.getBoolean("isAdmin", false);
            Long createdAt = sharedPreferences.getLong("createdAt", 0);
            Long updatedAt = sharedPreferences.getLong("updatedAt", 0);
//        Boolean statusUser = sharedPreferences.getBoolean("status_user", false);

            List<String> notificationIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("notification_ids", ""), ","));
            List<String> orderIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("order_ids", ""), ","));
            List<String> paymentIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("payment_ids", ""), ","));
            List<String> laundryIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("laundry_ids", ""), ","));
            List<String> serviceIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("service_ids", ""), ","));
            List<String> employeeIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("employee_ids", ""), ","));
            List<String> branchIds = Arrays.asList(TextUtils.split(sharedPreferences.getString("branch_ids", ""), ","));

            return new Customer(id, name, username, phone, address, email, password, idLaundry, idBranch, role, uid, photo,
                    status, registrationToken, whatsappVerified, verified, isAdmin, createdAt, updatedAt, false,
                    notificationIds, orderIds, paymentIds, laundryIds, serviceIds, employeeIds, branchIds);
        } else {
            Customer customer = new CustomerBuilder().build();
            writeCustomer(customer);
            return customer;
        }

    }


}
