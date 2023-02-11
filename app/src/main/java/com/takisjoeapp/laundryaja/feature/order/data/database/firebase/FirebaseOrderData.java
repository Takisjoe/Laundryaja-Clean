package com.takisjoeapp.laundryaja.feature.order.data.database.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.takisjoeapp.laundryaja.database.firebase.CollectionFirestore;
import com.takisjoeapp.laundryaja.database.firebase.DatabaseFirestoreApp;
import com.takisjoeapp.laundryaja.database.firebase.FirestoreApp;
import com.takisjoeapp.laundryaja.database.firebase.OnDatabaseFirebaseListener;
import com.takisjoeapp.laundryaja.database.virtual.DatabaseVirtualDataApp;
import com.takisjoeapp.laundryaja.feature.order.domain.entitas.Order;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;

import java.util.HashMap;
import java.util.Map;

public class FirebaseOrderData {
    String LOG = "FirebaseOrderData";

    //Database
    private FirestoreApp firestore;
    private DatabaseVirtualDataApp virtualDataApp;

    //Mode
    private final boolean isProduction;

    //Penanggung Jawab
    private final User user;

    public FirebaseOrderData(User user, ModeService modeService) {
        DebugLog.RULES("Memanggil divisi FirebaseOrderData", LOG);
        this.user = user;
        if (modeService == ModeService.DEBUG) {
            virtualDataApp = new DatabaseVirtualDataApp(modeService.getContext());
            isProduction = false;
        } else {
            isProduction = true;
            firestore = new DatabaseFirestoreApp();
        }
    }


    public void create(Order order, OnFirebaseDataOrder onFirebaseDataOrder) { //Testing OK
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.ORDERS(user);

        //Pembuatan ID
        String id = collectionReference.document().getId().replace("-", "c");
        order.setId(order.getLaundryId() + "-" + order.getBranchId() + "-" + id);

        //Membuat Metadata
        Map<String, Object> map = new HashMap<>();
        map.put("id", order.getId());
        map.put("customerId", order.getCustomerId());
        map.put("employyeId", order.getEmployyeId());
        map.put("laundryId", order.getLaundryId());
        map.put("branchId", order.getBranchId());
        map.put("serviceIds", order.getServiceIds());
        map.put("orderDate", order.getOrderDate());
        map.put("completionDate", order.getCompletionDate());
        map.put("catatan", order.getCatatan());
        map.put("note", order.getNote());
        map.put("totalPrice", order.getTotalPrice());
        map.put("adminFee", order.getAdminFee());
        map.put("paymentMethod", order.getPaymentMethod());
        map.put("status", order.getStatus());

        //Send
        if (isProduction) {
            firestore.create(collectionReference, order.getId(), map, new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    onFirebaseDataOrder.onSuccess();

                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    onFirebaseDataOrder.onFailure(e.getMessage());
                }
            });
        } else {
            virtualDataApp.create(map);
            onFirebaseDataOrder.onSuccess();
        }
    }

    public void update(Order order, OnFirebaseDataOrder onFirebaseDataOrder) {
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.ORDERS(user);

        //Membuat metadata
        Map<String, Object> map = new HashMap<>();
        map.put("id", order.getId());
        map.put("customerId", order.getCustomerId());
        map.put("employyeId", order.getEmployyeId());
        map.put("laundryId", order.getLaundryId());
        map.put("branchId", order.getBranchId());
        map.put("serviceIds", order.getServiceIds());
        map.put("orderDate", order.getOrderDate());
        map.put("completionDate", order.getCompletionDate());
        map.put("catatan", order.getCatatan());
        map.put("note", order.getNote());
        map.put("totalPrice", order.getTotalPrice());
        map.put("adminFee", order.getAdminFee());
        map.put("paymentMethod", order.getPaymentMethod());
        map.put("status", order.getStatus());

        //Memeriksa id
        if (order.getId() == null) {
            DebugLog.RULES("Id tidak ditemukan", LOG);
            onFirebaseDataOrder.onFailure("Gagal melakukan perubahan");
            return;
        }

        //Send
        if (isProduction) {
            firestore.update(collectionReference, order.getId(), map, new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    DebugLog.RULES("Berhasil mengupdate " + order.getId(), LOG);
                    onFirebaseDataOrder.onSuccess();

                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    DebugLog.RULES("Gagal mengupdate " + order.getId() + " - " + e.getMessage(), LOG);
                    onFirebaseDataOrder.onFailure(e.getMessage());
                }
            });
        } else {
            virtualDataApp.update(order.getId(), map);
            onFirebaseDataOrder.onSuccess();
        }


    }

    public void delete(Order order, OnFirebaseDataOrder onFirebaseDataOrder) {
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.ORDERS(user);

        //Pemeriksaan Id
        if (order.getId() == null) {
            DebugLog.RULES("Id tidak ditemukan", LOG);
            onFirebaseDataOrder.onFailure("Gagal menghapus order");

        }

        //Send
        if (isProduction) {
            firestore.delete(collectionReference, order.getId(), new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    DebugLog.RULES("Berhasil menghapus " + id, LOG);
                    onFirebaseDataOrder.onSuccess();
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    DebugLog.RULES("Gagal menghapus " + order.getId() + " - " + e.getMessage(), LOG);
                    onFirebaseDataOrder.onFailure(e.getMessage());
                }
            });
        } else {
            virtualDataApp.delete(order.getId());
            onFirebaseDataOrder.onSuccess();
        }
    }

    public void getData(OnNotifyDataOrder onNotifyDataOrder) {
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.ORDERS(user);

        if (isProduction) {
            firestore.dataRealTime(collectionReference, new OnDatabaseFirebaseListener() {
                @Override
                public void getDataNew(QueryDocumentSnapshot data) {
                    super.getDataNew(data);
                    Order order = data.toObject(Order.class);
                    onNotifyDataOrder.onNotify(order);
                    DebugLog.RULES("Notify : Order Baru " + order.getId(), LOG);
                }

                @Override
                public void getDataModify(QueryDocumentSnapshot data) {
                    super.getDataModify(data);
                    Order order = data.toObject(Order.class);
                    onNotifyDataOrder.onNotifyModify(order);
                    DebugLog.RULES("Notify : Order Update " + order.getId(), LOG);
                }

                @Override
                public void getDataRemove(QueryDocumentSnapshot data) {
                    super.getDataRemove(data);
                    Order order = data.toObject(Order.class);
                    onNotifyDataOrder.onNotifyRemove(order);
                    DebugLog.RULES("Notify : Order Delete " + order.getId(), LOG);
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    onNotifyDataOrder.onFailure(e.getMessage());
                }
            });
        } else {
            virtualDataApp.read();
        }

    }

}
