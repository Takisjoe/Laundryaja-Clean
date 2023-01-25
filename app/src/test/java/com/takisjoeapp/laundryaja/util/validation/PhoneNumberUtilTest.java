package com.takisjoeapp.laundryaja.util.validation;

import junit.framework.TestCase;

public class PhoneNumberUtilTest extends TestCase {

    public void testIsValidPhoneNumber() {
        assertTrue(PhoneNumberUtil.isValidPhoneNumber("6281338332023"));
    }

    public void testFormatPhoneNumber() {
        System.out.println(PhoneNumberUtil.formatPhoneNumber("081338332023","E.164"));
    }
}