package com.takisjoeapp.laundryaja.util.validation;

import junit.framework.TestCase;

public class WhatsappUtilsTest extends TestCase {
    public void testIsValidWhatsAppNumber() {
        assertTrue(WhatsappUtils.isValidWhatsAppNumber("08133833202"));
    }

}