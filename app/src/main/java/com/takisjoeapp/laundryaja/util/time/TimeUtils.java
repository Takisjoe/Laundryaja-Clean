package com.takisjoeapp.laundryaja.util.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        return new Date().getTime();
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
     * @param format Format tanggal yang diinginkan (mis. "dd-MM-yyyy")
     * @return String tanggal dalam format yang ditentukan
     */
    public static String formatDate(Long timestamp, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(timestamp);
    }


    /**
     * Method ini digunakan untuk mengembalikan waktu relatif dari input timeInMillis.
     * Waktu akan dikembalikan dalam format 'baru saja', 'x menit lalu', 'kemarin', 'x hari lagi', 'x minggu lalu', atau 'x bulan lalu'
     *
     * @param timeInMillis waktu yang ingin dihitung relatifnya dalam milisecond
     * @return waktu relatif dalam format string
     */
    public static String getRelativeLastTime(long timeInMillis) {
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

        if (diff >= 0) {
            if (diff < MINUTE_MILLIS) {
                return "1 menit lagi";
            } else if (diff < HOUR_MILLIS) {
                return (diff / MINUTE_MILLIS) + " menit lagi";
            } else if (diff < DAY_MILLIS) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                String formattedDate = dateFormat.format(calendar.getTime());
                return "Hari ini, pukul " + formattedDate;
            } else if (diff < 2 * DAY_MILLIS) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                String formattedDate = dateFormat.format(calendar.getTime());
                return "Besok, pukul " + formattedDate;
            } else if (diff < WEEK_MILLIS) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM, HH:mm");
                String formattedDate = dateFormat.format(calendar.getTime());
                return formattedDate;
            } else if (diff < MONTH_MILLIS) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM, HH:mm");
                String formattedDate = dateFormat.format(calendar.getTime());
                return formattedDate;
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timeInMillis);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm");
                String formattedDate = dateFormat.format(calendar.getTime());
                return formattedDate;
            }
        } else {
            diff = -diff;
            if (diff < MINUTE_MILLIS) {
                return "Terlambat beberapa detik yang lalu";
            } else if (diff < HOUR_MILLIS) {
                return "Terlambat " + (diff / MINUTE_MILLIS) + " menit yang lalu";
            } else if (diff < DAY_MILLIS) {
                return "Terlambat " + (diff / HOUR_MILLIS) + " jam yang lalu";
            } else if (diff < 2 * DAY_MILLIS) {
                return "kemarin";
            } else if (diff < WEEK_MILLIS) {
                return "Terlambat " + (diff / DAY_MILLIS) + " hari yang lalu";
            } else if (diff < MONTH_MILLIS) {
                return "Terlambat " + (diff / WEEK_MILLIS) + " minggu yang lalu";
            } else {
                return "Terlambat " + (diff / MONTH_MILLIS) + " bulan yang lalu";
            }
        }
    }
}
