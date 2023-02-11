package com.takisjoeapp.laundryaja.util.validation;

import junit.framework.TestCase;

public class PhoneNumberUtilsTest extends TestCase {

    public void testIsValidPhoneNumber() {
        System.out.println(PhoneNumberUtils.isValidPhoneNumber("+6281338332023"));
    }

    public void testFormatPhoneNumber() {
        System.out.println(PhoneNumberUtils.formatPhoneNumber("081332023"));
    }

    public void testSuccess() {

        String number = "081338332023";

        if (PhoneNumberUtils.isValidPhoneNumber(number)) {
            System.out.println(PhoneNumberUtils.formatPhoneNumber(number));

        }else {
            System.out.println("Nomor tidak valid");
        }
    }

    public void testFailure() {
        String number = "01338332023";

        if (PhoneNumberUtils.isValidPhoneNumber(number)) {
            System.out.println(PhoneNumberUtils.formatPhoneNumber(number));

        }else {
            System.out.println("Nomor tidak valid");
        }
    }
}