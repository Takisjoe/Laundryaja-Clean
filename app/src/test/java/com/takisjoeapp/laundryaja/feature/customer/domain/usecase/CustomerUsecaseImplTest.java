package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;

import junit.framework.TestCase;

public class CustomerUsecaseImplTest extends TestCase {

    private CustomerUseCase customerUseCase;
    private Customer customer1 = new CustomerBuilder().setId("id-1").setName("nama-1").build();
    private Customer customer2 = new CustomerBuilder().setId("id-2").setName("nama-2").build();
    private Customer customer3 = new CustomerBuilder().setId("id-3").setName("nama-3").build();
    private Customer customer4 = new CustomerBuilder().setId("id-4").setName("nama-4").build();
    private Customer customer5 = new CustomerBuilder().setId("id-5").setName("nama-5").build();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        customerUseCase = new CustomerUsecaseImpl();

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }

    public void testAddCustomer() {
        customerUseCase.addCustomer(customer1, new CustomerUseCase.OnAddCustomerCallback() {
            @Override
            public void onSuccess(boolean isSuccess) {
                assertTrue(isSuccess);
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
        customerUseCase.addCustomer(customer2, new CustomerUseCase.OnAddCustomerCallback() {
            @Override
            public void onSuccess(boolean isSuccess) {
                assertTrue(isSuccess);
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
        customerUseCase.addCustomer(customer3, new CustomerUseCase.OnAddCustomerCallback() {
            @Override
            public void onSuccess(boolean isSuccess) {
                assertTrue(isSuccess);
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
    }

    public void testGetCustomerById() {
        testAddCustomer();
        customerUseCase.getCustomerById("id-8", new CustomerUseCase.OnGetCustomerCallback() {
            @Override
            public void onSuccess(Customer customer) {
                System.out.println(customer.getName());
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
    }

    public void testUpdateCustomer() {
        testAddCustomer();
        customer3.setName("Celvinanda");
        customerUseCase.updateCustomer(customer3, new CustomerUseCase.OnUpdateCustomerCallback() {
            @Override
            public void onSuccess(boolean isSuccess, Customer beforeUpdate, Customer afterUpdate) {
                System.out.println("Before : "+beforeUpdate.getName());
                System.out.println("After : "+afterUpdate.getName());
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
    }

    public void testDeleteCustomer() {
        testAddCustomer();
        customerUseCase.deleteCustomer("id-2", new CustomerUseCase.OnDeleteCustomerCallback() {
            @Override
            public void onSuccess(boolean isSuccess, Customer customer_bin) {
                System.out.println(customer_bin.getName()+" berhasil dihapus");
            }

            @Override
            public void onFailed(String message) {
                System.out.println(message);
            }
        });
    }
}