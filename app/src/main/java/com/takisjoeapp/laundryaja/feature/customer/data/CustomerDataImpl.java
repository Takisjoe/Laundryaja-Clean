package com.takisjoeapp.laundryaja.feature.customer.data;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.takisjoeapp.laundryaja.feature.customer.data.database.PreferencesCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.RoomCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.FirestoreCustomerData;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.OnFirebaseDataCustomer;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.OnNotifyDataCustomer;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.OnQueryDataCustomer;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.feature.user.helper.UserBuilder;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.servicelocator.ServiceLocator;

import java.util.ArrayList;
import java.util.List;

public class CustomerDataImpl implements CustomerData {
    public String LOG = "CustomerDataImpl";

    private final FirestoreCustomerData firestoreCustomerData;
    private final PreferencesCustomerData preferencesCustomerData;
    private final RoomCustomerData roomCustomerData;
    private final User user;

    public CustomerDataImpl(User user, ModeService modeService) {
        this.user = user;

        if (modeService == ModeService.DEBUG) {
            DebugLog.DEBUG("Sedang menyiapkan konstruktor CustomerData", LOG);
        } else {
            DebugLog.DEBUG("Sedang menyiapkan konstruktor CustomerData", LOG);
        }

        Application application = ServiceLocator.getService(CustomerData.DI_Application);
        firestoreCustomerData = new FirestoreCustomerData(modeService);
        preferencesCustomerData = new PreferencesCustomerData(application);
        roomCustomerData = new RoomCustomerData(application);
    }

    public CustomerDataImpl(Application application) {
        DebugLog.ROOT("Menyiapkan Booting Notify CustomerData", LOG);
        this.user = new UserBuilder().build();

        firestoreCustomerData = new FirestoreCustomerData(ModeService.ROOT);
        preferencesCustomerData = new PreferencesCustomerData(application);
        roomCustomerData = new RoomCustomerData(application);
        getData();
    }

    @Override
    public void create(Customer customer, OnCustomerDataListener.Create create) {
        //Membuat NotificationList
        List<String> strings = new ArrayList<>();
        strings.add(user.getIdLaundry());

        //Menambahkan value dengan id Laundry
        customer.setNotification_ids(strings);

        firestoreCustomerData.create(customer, new OnFirebaseDataCustomer() {
            @Override
            public void onSuccess() {
                create.onSuccess(customer);
            }

            @Override
            public void onFailure(Exception e) {
                create.onFailure(e);
            }
        });
    }

    @Override
    public void update(Customer customer, OnCustomerDataListener.Update update) {
        firestoreCustomerData.update(customer, new OnFirebaseDataCustomer() {
            @Override
            public void onSuccess() {
                update.onSuccess(customer);
            }

            @Override
            public void onFailure(Exception e) {
                update.onFailure(e);
            }
        });
    }

    @Override
    public void delete(Customer customer, OnCustomerDataListener.Delete delete) {
        firestoreCustomerData.delete(customer, new OnFirebaseDataCustomer() {
            @Override
            public void onSuccess() {
                delete.onSuccess(customer);
            }

            @Override
            public void onFailure(Exception e) {
                delete.onFailure(e);
            }
        });
    }

    @Override
    public void search(String value, OnCustomerDataListener.Search search) {
        firestoreCustomerData.searchByPhone(value, new OnQueryDataCustomer() {
            @Override
            public void onSuccess(List<Customer> customerList) {
                DebugLog.RULES(value+" Ditemukan : " + customerList.size(), LOG);
                search.onSuccess(customerList);
            }

            @Override
            public void onFailure(Exception e) {
                search.onFailure(e);
            }
        });
    }

    @Override
    public void getData(OnCustomerDataListener.ReadAll readAll) {
        LifecycleOwner owner = ServiceLocator.getService(CustomerData.DI_LifecycleOwner);
        try {
            roomCustomerData.read().observe(owner, new Observer<List<Customer>>() {
                @Override
                public void onChanged(List<Customer> customers) {
                    readAll.onSuccess(customers);
                }
            });
        } catch (Exception e) {
            readAll.onFailure(e.getMessage());
        }
    }

    @Override
    public void getData(OnCustomerDataListener.GetData getData) {
        firestoreCustomerData.getData(user.getIdLaundry(), new OnNotifyDataCustomer() {
            @Override
            public void onNotify(Customer customer) {
                roomCustomerData.insertCustomer(customer);
                getData.onNotify(customer);
            }

            @Override
            public void onNotifyModify(Customer customer) {
                for (int i = 0; i < customer.getNotification_ids().size(); i++) {
                    if (customer.getNotification_ids().get(i).contains(user.getIdLaundry())) {
                        roomCustomerData.insertCustomer(customer);
                    } else {
                        roomCustomerData.delete(customer);
                    }
                }
                getData.onNotifyModify(customer);
            }

            @Override
            public void onNotifyRemove(Customer customer) {
                roomCustomerData.delete(customer);
                getData.onNotifyRemove(customer);
            }

            @Override
            public void onFailure(Exception e) {
                getData.onFailure(e);
            }
        });
    }

    private void getData() {
        DebugLog.RULES("Memanggil getData private", LOG);
        getData(new OnCustomerDataListener.GetData() {
            @Override
            public void onNotify(Customer customer) {

            }

            @Override
            public void onNotifyModify(Customer customer) {

            }

            @Override
            public void onNotifyRemove(Customer customer) {

            }

            @Override
            public void onFailure(Exception error) {

            }
        });
    }
}
