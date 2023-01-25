# Entitas Customer

## Deskripsi
Entitas ini digunakan untuk menyimpan informasi tentang pelanggan aplikasi Laundryaja.

## Atribut
- `id`: ID unik dari customer, digunakan sebagai primary key
- `name`: Nama lengkap dari customer
- `username`: Nama unik yang digunakan sebagai email customer (username@laundryaja.com)
- `phone`: Nomor telepon customer yang digunakan untuk verifikasi OTP dan komunikasi
- `address`: Alamat customer
- `createdAt`: Tanggal dan waktu customer ditambahkan ke dalam sistem
- `orders`: Daftar ID dari order yang dipesan oleh customer

## Contoh Penggunaan
Customer customer = new Customer("123", "John Doe", "johndoe", "+1234567890", "Jl. Example 123", Timestamp.now(), listOfOrders);

Perhatikan bahwa atribut `orders` merupakan list of order yang dipesan oleh customer, Anda dapat menggunakan `order.getId()` untuk mengambil ID order.

Jangan lupa untuk menambahkan validasi pada atribut seperti `phone` dan `username` sebelum menambahkan customer ke dalam sistem.

Ingat untuk selalu memperbarui dan mengecek dokumentasi ini seiring perkembangan proyek Anda.

Jika Anda memiliki pertanyaan atau kesulitan, jangan ragu untuk menghubungi saya.
