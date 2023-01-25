package com.takisjoeapp.laundryaja.util.validation;

import androidx.annotation.NonNull;

public interface WhatsappUtils {

    static boolean isValidWhatsAppNumber(@NonNull String phoneNumber) {
        if (phoneNumber.length() < 11) {
            return false;
        }
        for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
