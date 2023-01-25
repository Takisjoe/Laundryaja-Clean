package com.takisjoeapp.laundryaja.feature.customer.domain.repository;

import com.takisjoeapp.laundryaja.feature.customer.data.CustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.CustomerDataImpl;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import junit.framework.TestCase;

public class CustomerRepositoryTest extends TestCase {

    public void testAddCustomer() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = new CustomerBuilder().setId("id-1").build();
        assertTrue("Gagal Menambah",customerRepository.addCustomer(customer));
    }

    public void testAddCustomerFail() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = null;
        System.out.println(customerRepository.addCustomer(customer));
    }

    public void testGetCustomerById() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer1 = new CustomerBuilder().setId("id-1").build();
        Customer customer2 = new CustomerBuilder().setId("id-2").build();
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer1));
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer2));

        System.out.println(customerRepository.getCustomerById("id-2").getId());
    }

    public void testUpdateCustomer() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer1 = new CustomerBuilder().setId("id-1").setName("Nama-1").build();
        Customer customer2 = new CustomerBuilder().setId("id-2").setName("Nama-2").build();
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer1));
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer2));
        System.out.println(customerRepository.getCustomerById("id-1").getName());
        Customer customer = new CustomerBuilder().setId("id-1").setName("Celvinanda").build();
        System.out.println(customerRepository.updateCustomer(customer));
        System.out.println(customerRepository.getCustomerById("id-1").getName());
    }

    public void testDeleteSuccess() {
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer1 = new CustomerBuilder().setId("id-1").setName("Nama-1").build();
        Customer customer2 = new CustomerBuilder().setId("id-2").setName("Nama-2").build();
        Customer customer3 = new CustomerBuilder().setId("id-3").setName("Nama-3").build();
        Customer customer4 = new CustomerBuilder().setId("id-4").setName("Nama-4").build();
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer1));
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer2));
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer3));
        assertTrue("Gagal menambah",customerRepository.addCustomer(customer4));
        System.out.println(customerRepository.deleteCustomer("id-3"));
        for (int i = 0; i < customerRepository.getAll().getValue().size(); i++) {
            System.out.println(customerRepository.getAll().getValue().get(i).getName());
        }
    }


}





