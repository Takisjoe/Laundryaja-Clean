package com.takisjoeapp.laundryaja.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    private static final Date currentDate = new Date();
    private static final long timestamp = currentDate.getTime();

    private static final long MINUTE_MILLIS = 60 * 1000;
    private static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final long DAY_MILLIS = 24 * HOUR_MILLIS;
    private static final long WEEK_MILLIS = 7 * DAY_MILLIS;
    private static final long MONTH_MILLIS = 4 * WEEK_MILLIS;

    /**
     * @return long Timestamp saat ini
     */
    public static long getTimestamp() {
        return timestamp;
    }

    /**
     * Membuat objek Date untuk saat ini
     *
     * @return objek Date saat ini
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * Mengembalikan tanggal sekarang dalam format yang ditentukan
     *
     * @param format Format tanggal yang diinginkan (mis. "dd-MM-yyyy")
     * @return String tanggal sekarang dalam format yang ditentukan
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(new Date());
    }

    /**
     * Mengembalikan waktu sekarang dalam format yang ditentukan
     *
     * @param format Format waktu yang diinginkan (mis. "HH:mm:ss")
     * @return String waktu sekarang dalam format yang ditentukan
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat timeFormat = new SimpleDateFormat(format, Locale.getDefault());
        return timeFormat.format(new Date());
    }

    /**
     * Mengembalikan waktu saat ini dalam format yang ditentukan
     *
     * @param format format waktu yang diinginkan, contoh: "dd/MM/yyyy HH:mm:ss"
     * @return waktu saat ini dalam format yang ditentukan
     */
    public static String getFormattedTime(String format) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * Mengembalikan tanggal dalam format yang ditentukan
     *
     * @param date   tanggal yang ingin diubah formatnya
     * @param format Format tanggal yang diinginkan (mis. "dd-MM-yyyy")
     * @return String tanggal dalam format yang ditentukan
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     * Method ini digunakan untuk mengembalikan waktu relatif dari input timeInMillis.
     * Waktu akan dikembalikan dalam format 'baru saja', 'x menit lalu', 'kemarin', 'x hari lagi', 'x minggu lalu', atau 'x bulan lalu'
     *
     * @param timeInMillis waktu yang ingin dihitung relatifnya dalam milisecond
     * @return waktu relatif dalam format string
     */
    public static  String getRelativeLastTime(long timeInMillis) {
        long currentTime = System.currentTimeMillis();
        long diff = currentTime - timeInMillis;
        if (diff < MINUTE_MILLIS) {
            return "baru saja";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1 menit lalu";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " menit lalu";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "1 jam lalu";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " jam lalu";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "kemarin";
        } else if (diff < 7 * DAY_MILLIS) {
            return diff / DAY_MILLIS + " hari lalu";
        } else if (diff < 2 * WEEK_MILLIS) {
            return "1 minggu lalu";
        } else if (diff < MONTH_MILLIS) {
            return diff / WEEK_MILLIS + " minggu lalu";
        } else if (diff < 2 * MONTH_MILLIS) {
            return "1 bulan lalu";
        } else {
            return diff / MONTH_MILLIS + " bulan lalu";
        }
    }

    /**
     * Mengembalikan string waktu relatif dari waktu yang diberikan
     *
     * @param timeInMillis waktu yang ingin diformat dalam milisecond
     * @return string waktu relatif, misalnya "1 menit lagi", "1 jam lagi", "hari ini", "besok", "2 hari lagi", "1 minggu lagi", "1 bulan lagi"
     */
    public static String getRelativeTimeToCome(long timeInMillis) {
        long now = System.currentTimeMillis();
        long diff = timeInMillis - now;

        if (diff < MINUTE_MILLIS) {
            return "1 menit lagi";
        } else if (diff < HOUR_MILLIS) {
            return "1 jam lagi";
        } else if (diff < DAY_MILLIS) {
            return "hari ini";
        } else if (diff < 2 * DAY_MILLIS) {
            return "besok";
        } else if (diff < WEEK_MILLIS) {
            return (diff / DAY_MILLIS) + " hari lagi";
        } else if (diff < MONTH_MILLIS) {
            return (diff / WEEK_MILLIS) + " minggu lagi";
        } else {
            return (diff / MONTH_MILLIS) + " bulan lagi";
        }
    }
}
