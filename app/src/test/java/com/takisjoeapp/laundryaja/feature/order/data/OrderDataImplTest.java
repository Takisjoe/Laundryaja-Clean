package com.takisjoeapp.laundryaja.feature.order.data;

import android.content.Context;

import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.order.helper.OrderBuilder;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderDataImplTest extends TestCase {

    @Mock
    private Context context;

    @Test
    public void testCreateDebug() {
        User user = new UserBuilder().build();
        OrderData orderData = new OrderDataImpl(user, ModeService.DEBUG.setContext(context));

        Order order = new OrderBuilder().build();
        orderData.create(order, new OnOrderDataListener.CreateOrder() {
            @Override
            public void onSuccess() {
                System.out.println("Berhasil");
            }

            @Override
            public void onFailure(String error) {
                System.out.println("Gagal " + error);
            }
        });

    }
}