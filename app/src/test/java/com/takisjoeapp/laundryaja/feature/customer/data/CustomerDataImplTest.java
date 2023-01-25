package com.takisjoeapp.laundryaja.feature.customer.data;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import junit.framework.TestCase;

public class CustomerDataImplTest extends TestCase {

    public void testCreate() {
        CustomerData customerData = new CustomerDataImpl();
        Customer customer = new CustomerBuilder().setId("HAlo").build();
        boolean result = customerData.create(customer);
        assertTrue(result); // assertTrue untuk memastikan bahwa method create mengembalikan nilai true
    }

    public void testCreateFail() {
        CustomerData customerData = new CustomerDataImpl();
        Customer customer1 = new CustomerBuilder().setId("HAlo").build();
        Customer customer2 = new CustomerBuilder().setId("HAlo").build();
        assertTrue(customerData.create(customer1)); // assertTrue untuk memastikan bahwa method create mengembalikan nilai true
        assertFalse(customerData.create(customer2)); // assertFalse untuk memastikan bahwa method create mengembalikan nilai false
    }


    public void testUpdate() {
        CustomerData customerData = new CustomerDataImpl();
        Customer customer1 = new CustomerBuilder().setId("HAlo").build();
        customerData.create(customer1);
        Customer customer2 = new CustomerBuilder().setId("idNo2").build();
        customerData.create(customer2);
        for (int i = 0; i < customerData.read().size(); i++) {
            System.out.println(customerData.read().get(i).getId());
        }
        customerData.update("HAlo",customer2);
        for (int i = 0; i < customerData.read().size(); i++) {
            System.out.println(customerData.read().get(i).getId());
        }
    }


    public void testDelete() {
        CustomerData customerData = new CustomerDataImpl();
        Customer customer1 = new CustomerBuilder().setId("HAlo").build();
        customerData.create(customer1);
        Customer customer2 = new CustomerBuilder().setId("idNo2").build();
        customerData.create(customer2);
        for (int i = 0; i < customerData.read().size(); i++) {
            System.out.println(customerData.read().get(i).getId());
        }
        assertTrue(customerData.delete("HAlo"));
        for (int i = 0; i < customerData.read().size(); i++) {
            System.out.println(customerData.read().get(i).getId());
        }
    }

    public void testRead() {
    }
}