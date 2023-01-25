package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import androidx.lifecycle.LiveData;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.payment.domain.entitas.Payment;

import java.util.List;

public interface CustomerUseCase {
    /**
     * Menambahkan customer baru ke dalam sistem
     *
     * @param customer customer yang ingin ditambahkan
     * @param callback callback yang dipanggil setelah proses selesai
     */
    void addCustomer(Customer customer, OnAddCustomerCallback callback);

    interface OnAddCustomerCallback {
        void onSuccess(boolean isSuccess);

        void onFailed(String message);
    }

    /**
     * Mengambil customer berdasarkan ID
     *
     * @param id       ID customer yang ingin diambil
     * @param callback callback yang dipanggil setelah proses selesai
     */
    void getCustomerById(String id, OnGetCustomerCallback callback);

    interface OnGetCustomerCallback {
        void onSuccess(Customer customer);

        void onFailed(String message);
    }

    /**
     * Memperbarui data customer
     *
     * @param customer customer yang ingin diperbarui
     * @param callback callback yang dipanggil setelah proses selesai
     */
    void updateCustomer(Customer customer, OnUpdateCustomerCallback callback);

    interface OnUpdateCustomerCallback {
        void onSuccess(boolean isSuccess, Customer beforeUpdate, Customer afterUpdate);

        void onFailed(String message);
    }

    /**
     * Menghapus customer berdasarkan ID
     *
     * @param id       ID customer yang ingin dihapus
     * @param callback callback yang dipanggil setelah proses selesai
     */
    void deleteCustomer(String id, OnDeleteCustomerCallback callback);

    interface OnDeleteCustomerCallback {
        void onSuccess(boolean isSuccess, Customer customer_bin);

        void onFailed(String message);
    }

    /**
     * Mengambil daftar semua customer yang terdaftar di sistem
     *
     * @param callback callback yang digunakan untuk mengirimkan hasil dari operasi ini
     */
    LiveData<List<Customer>> getAllCustomers(OnGetAllCustomersCallback callback);

    interface OnGetAllCustomersCallback {
        // Callback ketika berhasil mengambil daftar customer
        void onSuccess(List<Customer> customers);

        // Callback ketika terjadi kesalahan dalam mengambil daftar customer
        void onError(String message);
    }

    /**
     * Melakukan pencarian customer berdasarkan keyword yang ditentukan.
     * Keyword dapat berupa nama, username, atau nomor telepon customer.
     *
     * @param keyword  kata kunci yang digunakan untuk melakukan pencarian
     * @param callback callback yang akan dipanggil setelah proses pencarian selesai
     */
    void searchCustomers(String keyword, OnSearchCustomersCallback callback);

    interface OnSearchCustomersCallback {
        // Callback ketika berhasil menemukan customer
        void onSuccess(List<Customer> customers);

        // Callback ketika tidak ditemukan customer yang cocok dengan keyword
        void onNotFound();

        // Callback ketika terjadi kesalahan dalam pencarian customer
        void onError(String message);
    }

    /**
     * Verifikasi nomor telepon customer menggunakan OTP.
     *
     * @param customerId ID customer yang ingin di verifikasi.
     * @param otp        Kode OTP yang diterima oleh customer.
     * @param callback   Callback yang akan dipanggil setelah proses verifikasi selesai.
     */
    void verifyPhoneNumber(String customerId, String otp, OnVerifyPhoneNumberCallback callback);


    interface OnVerifyPhoneNumberCallback {
        // Callback ketika berhasil melakukan verifikasi nomor telepon
        void onSuccess();

        // Callback ketika terjadi kesalahan dalam verifikasi nomor telepon
        void onError(String message);
    }

    /**
     * Mendapatkan riwayat order dari customer berdasarkan ID customer.
     *
     * @param customerId ID customer yang ingin dilihat riwayat order-nya.
     * @param callback   Callback yang akan dipanggil setelah proses selesai.
     */
    void getOrderHistory(String customerId, OnGetOrderHistoryCallback callback);

    interface OnGetOrderHistoryCallback {
        void onSuccess();

        void onError(String message);
    }

    /**
     * Mendapatkan daftar riwayat order dari customer
     *
     * @param customerId ID customer yang ingin dilihat riwayat order-nya
     * @param callback   callback untuk menangani hasil pengambilan data
     */
    void getCustomerOrderHistory(String customerId, OnGetCustomerOrderHistoryCallback callback);

    interface OnGetCustomerOrderHistoryCallback {
        /**
         * Dipanggil saat pengambilan data riwayat order berhasil
         *
         * @param orderHistory daftar riwayat order
         */
        void onSuccess(List<Order> orderHistory);

        /**
         * Dipanggil saat terjadi kesalahan saat pengambilan data riwayat order
         *
         * @param message pesan kesalahan
         */
        void onError(String message);
    }

    /**
     * Melakukan pembayaran untuk order customer
     *
     * @param orderId     ID order yang akan dibayar
     * @param payment data pembayaran
     * @param callback    callback untuk menangani hasil proses pembayaran
     */
    void makePayment(String orderId, Payment payment, OnMakePaymentCallback callback);

    interface OnMakePaymentCallback {
        /**
         * Dipanggil saat proses pembayaran berhasil
         *
         * @param paymentId ID pembayaran yang berhasil dilakukan
         */
        void onSuccess(String paymentId);

        /**
         * Dipanggil saat terjadi kesalahan saat proses pembayaran
         *
         * @param message pesan kesalahan
         */
        void onError(String message);
    }

    /**
     * Mendapatkan status order customer
     *
     * @param orderId  ID order yang ingin dilihat status-nya
     * @param callback callback untuk menangani hasil pengambilan data
     */
    void getOrderStatus(String orderId, OnGetOrderStatusCallback callback);

    interface OnGetOrderStatusCallback {
        /**
         * Dipanggil saat pengambilan data status order berhasil
         *
         * @param order status order
         */
        void onSuccess(Order order);

        /**
         * Dipanggil saat terjadi kesalahan saat pengambilan data status order
         *
         * @param message pesan kesalahan
         */
        void onError(String message);
    }
}




