##Fungsi
- addCustomer(Customer customer) : digunakan untuk menambahkan customer baru ke dalam database
- getCustomerById(String id) : digunakan untuk mengambil customer berdasarkan ID
- updateCustomer(Customer customer) : digunakan untuk memperbarui informasi customer
- deleteCustomer(String id) : digunakan untuk menghapus customer berdasarkan ID

##Aturan
- Semua input harus divalidasi sebelum disimpan ke dalam database
- Semua output harus divalidasi sebelum dikembalikan ke domain
- Semua operasi database harus dilakukan dalam thread yang berbeda dari main thread
- Semua operasi harus dilakukan dengan transaksi untuk menghindari kesalahan yang tidak diinginkan
- Semua operasi harus dilakukan dengan error handling yang sesuai

Note: Anda dapat menambahkan atau menyesuaikan fungsi dan aturan sesuai dengan kebutuhan aplikasi Anda.