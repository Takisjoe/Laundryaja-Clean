package com.takisjoeapp.laundryaja.feature.order.data.database;

import com.takisjoeapp.laundryaja.feature.order.data.database.firebase.FirebaseOrderData;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import junit.framework.TestCase;

public class FirebaseOrderDataTest extends TestCase {

    private FirebaseOrderData data;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        User user = new UserBuilder().build();
        data = new FirebaseOrderData(user, ModeService.DEBUG);
    }

    public void testCreate() {

    }
}