package com.takisjoeapp.laundryaja.util.validation;

public class PhoneNumberUtil {
    // Regex untuk validasi nomor telepon
    private static final String PHONE_REGEX = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

    /**
     * Memvalidasi nomor telepon yang diberikan menggunakan regex
     * @param phoneNumber nomor telepon yang ingin divalidasi
     * @return true jika nomor telepon valid, false jika tidak valid
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_REGEX);
    }

    /**
     * Mengubah format nomor telepon agar sesuai dengan format yang ditentukan
     * @param phoneNumber nomor telepon yang ingin diubah formatnya
     * @param format contoh format: +62 XXX-XXX-XXXX
     * @return nomor telepon dengan format yang ditentukan
     */
    public static String formatPhoneNumber(String phoneNumber, String format) {
        // Menghilangkan semua karakter selain angka
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        // Menambahkan leading 0 jika nomor telepon tidak memiliki cukup digit
        while (phoneNumber.length() < format.length()) {
            phoneNumber = "0" + phoneNumber;
        }
        // Mengganti placeholder (X) dalam format dengan digit nomor telepon
        String result = format;
        int i = 0;
        while (i < phoneNumber.length()) {
            int j = result.indexOf("X");
            if (j == -1) {
                break;
            }
            result = result.substring(0, j) + phoneNumber.charAt(i) + result.substring(j + 1);
            i++;
        }
        return result;
    }
}

