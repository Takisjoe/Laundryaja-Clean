package com.takisjoeapp.laundryaja.feature.customer.domain.usecase;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.customer.helper.CustomerBuilder;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import junit.framework.TestCase;

public class CustomerUseCaseImplTest extends TestCase {

    private CustomerUseCase customerUseCase;
    private Customer customer1 = new CustomerBuilder().setId("id-1").setName("nama-1").build();
    private Customer customer2 = new CustomerBuilder().setId("id-2").setName("nama-2").build();
    private Customer customer3 = new CustomerBuilder().setId("id-3").setName("nama-3").build();
    private Customer customer4 = new CustomerBuilder().setId("id-4").setName("nama-4").build();
    private Customer customer5 = new CustomerBuilder().setId("id-5").setName("nama-5").build();

    @Override
    public void setUp() throws Exception {
        super.setUp();
        customerUseCase = new CustomerUseCaseImpl(ModeService.DEBUG);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }

    public void testAddCustomer() {
//        customerUseCase.addCustomer("", "", "", new CustomerUseCaseImpl.OnAddCustomerCallback() {
//            @Override
//            public void onSuccess(Customer customer) {
//
//            }
//
//            @Override
//            public void onFailure(String error) {
//
//            }
//
//            @Override
//            public void OnErrorName(String message) {
//
//            }
//
//            @Override
//            public void OnErrorPhone(String message) {
//
//            }
//
//            @Override
//            public void OnErrorAlamat(String message) {
//
//            }
//        });
    }


}