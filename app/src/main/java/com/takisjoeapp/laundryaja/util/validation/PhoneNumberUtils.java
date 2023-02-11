package com.takisjoeapp.laundryaja.util.validation;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class PhoneNumberUtils {
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();


    public static String formatPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber, "ID");
            return phoneNumberUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return phoneNumber;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        try {
            Phonenumber.PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber, "ID");
            return phoneNumberUtil.isValidNumber(numberProto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
