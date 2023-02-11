package com.takisjoeapp.laundryaja.feature.order.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.order.data.database.RoomOrderDatabase;
import com.takisjoeapp.laundryaja.feature.order.data.database.firebase.FirebaseOrderData;
import com.takisjoeapp.laundryaja.feature.order.data.database.firebase.OnFirebaseDataOrder;
import com.takisjoeapp.laundryaja.feature.order.data.database.PreferencesOrderData;
import com.takisjoeapp.laundryaja.feature.order.data.database.firebase.OnNotifyDataOrder;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.List;

public class OrderDataImpl implements OrderData {
    private final String LOG = "OrderDataImpl";
    private final FirebaseOrderData firebaseOrderData;
    private final PreferencesOrderData preferencesOrderData;
    private final RoomOrderDatabase roomOrderDatabase;
    private final User user;


    public OrderDataImpl(@NonNull User user, ModeService modeService) {
        this.user = user;

        if (modeService == ModeService.DEBUG) {
            DebugLog.DEBUG("Sedang menyiapkan konstruktor OrderData", LOG);
        } else {
            DebugLog.RULES("Sedang menyiapkan konstruktor OrderData", LOG);
        }

        Application application = ServiceLocator.getService("AddOrder");
        firebaseOrderData = new FirebaseOrderData(user, modeService);
        preferencesOrderData = new PreferencesOrderData(application);
        roomOrderDatabase = new RoomOrderDatabase(application);
//        getData();
    }

    public OrderDataImpl(Application application) {
        DebugLog.ROOT("Menyiapkan Booting Notify OrderData", LOG);
        User user = new UserBuilder().build();
        this.user = user;

        firebaseOrderData = new FirebaseOrderData(user, ModeService.ROOT);
        preferencesOrderData = new PreferencesOrderData(application);
        roomOrderDatabase = new RoomOrderDatabase(application);
        getData();

    }

    @Override
    public Customer drafCustomer() {
        return preferencesOrderData.readCustomer();
    }

    @Override
    public void drafCustomer(Customer customer) {
        preferencesOrderData.writeCustomer(customer);
    }

    @Override
    public Order drafOrder() {
        return null;
    }

    @Override
    public void drafOrder(Order order) {

    }

    @Override
    public void create(Order order, OnOrderDataListener.CreateOrder orderListener) {
        order.setEmployyeId(user.getId());
        order.setLaundryId(user.getIdLaundry());
        order.setBranchId(user.getIdBranch());

        firebaseOrderData.create(order, new OnFirebaseDataOrder() {
            @Override
            public void onSuccess() {
                orderListener.onSuccess();
            }

            @Override
            public void onFailure(String error) {
                orderListener.onFailure(error);
            }
        });
    }

    @Override
    public void update(Order order, OnOrderDataListener.UpdateOrder updateOrder) {
        order.setEmployyeId(user.getId());
        order.setLaundryId(user.getIdLaundry());
        order.setBranchId(user.getIdBranch());

        firebaseOrderData.update(order, new OnFirebaseDataOrder() {
            @Override
            public void onSuccess() {
                updateOrder.onSuccess();
            }

            @Override
            public void onFailure(String error) {
                updateOrder.onFailure(error);
            }
        });
    }

    @Override
    public void delete(Order order, OnOrderDataListener.DeleteOrder deleteOrder) {
        firebaseOrderData.delete(order, new OnFirebaseDataOrder() {
            @Override
            public void onSuccess() {
                deleteOrder.onSuccess();
            }

            @Override
            public void onFailure(String error) {
                deleteOrder.onFailure(error);
            }
        });
    }

    @Override
    public void getData(OnOrderDataListener.GetData getData) {
        firebaseOrderData.getData(new OnNotifyDataOrder() {
            @Override
            public void onNotify(Order order) {
                getData.onNotify(order);
                roomOrderDatabase.replace(order);
            }

            @Override
            public void onNotifyModify(Order order) {
                getData.onNotifyModify(order);
                roomOrderDatabase.update(order);
            }

            @Override
            public void onNotifyRemove(Order order) {
                getData.onNotifyRemove(order);
                roomOrderDatabase.delete(order);
            }

            @Override
            public void onFailure(String error) {
                getData.onFailure(error);
            }
        });
    }

    @Override
    public void getData(OnOrderDataListener.ReadAll readAll) {
        LifecycleOwner owner = ServiceLocator.getService("LifecycleOwnerOrderData");
        try {
            roomOrderDatabase.read().observe(owner, new Observer<List<Order>>() {
                @Override
                public void onChanged(List<Order> orderList) {
                    readAll.onSuccess(orderList);
                }
            });
        } catch (Exception e) {
            readAll.onFailure(e.getMessage());
        }
    }

    @Override
    public LiveData<List<Order>> getData() {
        getData(new OnOrderDataListener.GetData() {
            @Override
            public void onNotify(Order order) {

            }

            @Override
            public void onNotifyModify(Order order) {

            }

            @Override
            public void onNotifyRemove(Order order) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
        return roomOrderDatabase.read();
    }

}