package com.takisjoeapp.laundryaja.feature.customer.data;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;

import java.util.List;

public class OnCustomerDataListener {

    public interface Create {
        void onSuccess(Customer customer);

        void onFailure(Exception e);
    }

    public interface Update {
        void onSuccess(Customer customer);

        void onFailure(Exception e);
    }

    public interface Delete {
        void onSuccess(Customer customer);

        void onFailure(Exception e);
    }

    public interface Search {
        void onSuccess(List<Customer> customers);

        void onFailure(Exception e);
    }

    public interface GetData {
        void onNotify(Customer customer);

        void onNotifyModify(Customer customer);

        void onNotifyRemove(Customer customer);

        void onFailure(Exception error);
    }

    public interface ReadAll {
        void onSuccess(List<Customer> orderList);

        void onFailure(String error);
    }

    public interface Draf {
        void onLoad(Customer customer);

        void onFailure(String error);
    }
}
