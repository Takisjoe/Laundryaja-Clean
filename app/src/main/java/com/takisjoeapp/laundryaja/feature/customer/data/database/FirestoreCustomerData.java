package com.takisjoeapp.laundryaja.feature.customer.data.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;

import java.util.HashMap;
import java.util.Map;

public class FirestoreCustomerData {

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final CollectionReference collection = db.collection("User");

    public Task<Void> addCustomer(@NonNull Customer customer) {
        //Generate ID from Firestore
        String generateID = collection.document().getId();

        Map<String, Object> map = new HashMap<>();
        map.put("id", generateID);
        map.put("name", customer.getName());
        map.put("username", customer.getUsername());
        map.put("phone", customer.getPhone());
        map.put("address", customer.getAddress());
        map.put("createdAt", customer.getCreatedAt());
        map.put("orders", customer.getOrders());

        return collection.document(generateID).set(map);

    }

    public Task<QuerySnapshot> getAllCustomer() {
        System.out.print("Memanggil getAllCustomer (FirestoreCustomerData) -> ");
        return collection.get();
    }

    public static void refreshData(OnrefreshDataListener dataListener) {
        collection.addSnapshotListener((value, e) -> {
            if (e != null) {
                Log.w("TAG", "listen:error", e);
                return;
            }

            for (DocumentChange dc : value.getDocumentChanges()) {
                System.out.println("Ada pembaruan");
                dataListener.onUpToDate();
                switch (dc.getType()) {
                    case ADDED:
                        Log.d("TAG", "New user: " + dc.getDocument().getData());
                        dataListener.onNew(dc.getDocument().getData());
                        break;
                    case MODIFIED:
                        Log.d("TAG", "Modified user: " + dc.getDocument().getData());
                        dataListener.onModified(dc.getDocument().getData());
                        break;
                    case REMOVED:
                        Log.d("TAG", "Removed user: " + dc.getDocument().getData());
                        dataListener.onRemove(dc.getDocument().getData());
                        break;
                }
            }
        });
    }

    public interface OnrefreshDataListener{
        void onUpToDate();
        void onNew(Map<String, Object> data);

        void onModified(Map<String, Object> data);

        void onRemove(Map<String, Object> data);
    }

}
