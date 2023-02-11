package com.takisjoeapp.laundryaja.feature.customer.data.database;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import junit.framework.TestCase;

public class VirtualCustomerDataTest extends TestCase {
    private final VirtualCustomerData data = new VirtualCustomerData();

    public void testCreate() {
        data.create(VirtualCustomerData.fakeCustomer());
        data.create(VirtualCustomerData.fakeCustomer());
        data.create(VirtualCustomerData.fakeCustomer());
        data.create(VirtualCustomerData.fakeCustomer());
        data.create(VirtualCustomerData.fakeCustomer());
        data.create(VirtualCustomerData.fakeCustomer());

        testRead();
    }

    public void testRead() {
        data.read();
    }

    public void testUpdate() {
        testCreate();
        data.update(new CustomerBuilder().setId("id-3").setName("Celvinanda Octiawan").build());

        data.read();
    }

    public void testDelete() {
        testCreate();
        data.delete(new CustomerBuilder().setId("id-3").setName("Celvinanda Octiawan").build());
        data.delete(new CustomerBuilder().setId("id-1").setName("Celvinanda Octiawan").build());
        data.delete(new CustomerBuilder().setId("id-7").setName("Celvinanda Octiawan").build());

        data.read();
    }

    public void testClear() {
        data.clearAll();
        testRead();
    }
}