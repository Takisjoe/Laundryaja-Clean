package com.takisjoeapp.laundryaja.feature.customer.data.database.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.takisjoeapp.laundryaja.database.firebase.CollectionFirestore;
import com.takisjoeapp.laundryaja.database.firebase.DatabaseFirestoreApp;
import com.takisjoeapp.laundryaja.database.firebase.FirestoreApp;
import com.takisjoeapp.laundryaja.database.firebase.OnDatabaseFirebaseListener;
import com.takisjoeapp.laundryaja.database.virtual.DatabaseVirtualDataApp;
import com.takisjoeapp.laundryaja.feature.customer.domain.entitas.Customer;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;
import com.takisjoeapp.laundryaja.util.debug.ModeService;
import com.takisjoeapp.laundryaja.util.validation.PhoneNumberUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreCustomerData {
    private final String LOG = "FirestoreCustomerData";

    //Database
    private FirestoreApp firestoreApp;
    private DatabaseVirtualDataApp virtualDataApp;

    //MODE
    private final boolean isProduction;

    public FirestoreCustomerData(ModeService modeService) {
        DebugLog.RULES("Memanggil devisi FirestoreCustomerData", LOG);
        if (modeService == ModeService.DEBUG) {
            virtualDataApp = new DatabaseVirtualDataApp(modeService.getContext());
            isProduction = false;
        } else {
            firestoreApp = new DatabaseFirestoreApp();
            isProduction = true;
        }
    }

    public void create(Customer customer, OnFirebaseDataCustomer onFirebaseDataCustomer) {
        CollectionReference collectionReference = CollectionFirestore.USERS();

        //Pengecekan format number
        if (PhoneNumberUtils.isValidPhoneNumber(customer.getPhone())) {
            customer.setPhone(PhoneNumberUtils.formatPhoneNumber(customer.getPhone()));
        } else {
            onFirebaseDataCustomer.onFailure(new Exception("Nomor tidak valid"));
            return;
        }

        //Pembuatan ID
        String id = collectionReference.document().getId().replace("-", "c");
        customer.setId(id);

        //Membuat Metadata
        Map<String, Object> map = new HashMap<>();
        map.put("id", customer.getId());
        map.put("name", customer.getName());
        map.put("username", customer.getUsername());
        map.put("phone", customer.getPhone());
        map.put("address", customer.getAddress());
        map.put("email", customer.getEmail());
        map.put("idLaundry", customer.getIdLaundry());
        map.put("idBranch", customer.getIdBranch());
        map.put("role", customer.getRole());
        map.put("uid", customer.getUid());
        map.put("photo", customer.getPhoto());
        map.put("status", customer.getStatus());
        map.put("registrationToken", customer.getRegistrationToken());
        map.put("whatsapp_verified", customer.isWhatsapp_verified());
        map.put("verified", customer.isVerified());
        map.put("isAdmin", customer.isAdmin());
        map.put("createdAt", customer.getCreatedAt());
        map.put("updatedAt", customer.getUpdatedAt());
        map.put("status_user", customer.isStatus_user());
        map.put("notification_ids", customer.getNotification_ids());
        map.put("order_ids", customer.getOrder_ids());
        map.put("payment_ids", customer.getPayment_ids());
        map.put("laundry_ids", customer.getLaundry_ids());
        map.put("service_ids", customer.getService_ids());
        map.put("employee_ids", customer.getEmployee_ids());
        map.put("branch_ids", customer.getBranch_ids());

        //Send
        if (isProduction) {
            firestoreApp.create(collectionReference, customer.getId(), map, new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    onFirebaseDataCustomer.onSuccess();
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    onFirebaseDataCustomer.onFailure(e);
                }
            });
        } else {
            virtualDataApp.create(map);
            onFirebaseDataCustomer.onSuccess();
        }
    }

    public void update(Customer customer, OnFirebaseDataCustomer onFirebaseDataCustomer) {
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.USERS();

        //Membuat update metadata
        Map<String, Object> map = new HashMap<>();
        map.put("id", customer.getId());
        map.put("name", customer.getName());
        map.put("username", customer.getUsername());
        map.put("phone", customer.getPhone());
        map.put("address", customer.getAddress());
        map.put("email", customer.getEmail());
        map.put("idLaundry", customer.getIdLaundry());
        map.put("idBranch", customer.getIdBranch());
        map.put("role", customer.getRole());
        map.put("uid", customer.getUid());
        map.put("photo", customer.getPhoto());
        map.put("status", customer.getStatus());
        map.put("registrationToken", customer.getRegistrationToken());
        map.put("whatsapp_verified", customer.isWhatsapp_verified());
        map.put("verified", customer.isVerified());
        map.put("isAdmin", customer.isAdmin());
        map.put("createdAt", customer.getCreatedAt());
        map.put("updatedAt", customer.getUpdatedAt());
        map.put("status_user", customer.isStatus_user());
        map.put("notification_ids", customer.getNotification_ids());
        map.put("order_ids", customer.getOrder_ids());
        map.put("payment_ids", customer.getPayment_ids());
        map.put("laundry_ids", customer.getLaundry_ids());
        map.put("service_ids", customer.getService_ids());
        map.put("employee_ids", customer.getEmployee_ids());
        map.put("branch_ids", customer.getBranch_ids());

        //Memeriksa id
        if (customer.getId() == null) {
            DebugLog.RULES("Id tidak ditemukan", LOG);
            onFirebaseDataCustomer.onFailure(new Exception("Gagal melakukan perubahan"));
            return;
        }

        if (isProduction) {
            firestoreApp.update(collectionReference, customer.getId(), map, new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    DebugLog.RULES("Berhasil mengupdate " + customer.getId(), LOG);
                    onFirebaseDataCustomer.onSuccess();
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    DebugLog.RULES("Gagal mengupdate " + customer.getId() + " - " + e.getMessage(), LOG);

                    onFirebaseDataCustomer.onFailure(e);
                }
            });
        } else {
            virtualDataApp.update(customer.getId(), map);
            onFirebaseDataCustomer.onSuccess();
        }
    }

    public void delete(Customer customer, OnFirebaseDataCustomer onFirebaseDataCustomer) {
        //Pemanggilan Alamat
        CollectionReference collectionReference = CollectionFirestore.USERS();

        if (customer.getId() == null) {
            DebugLog.RULES("Id tidak ditemukan", LOG);
            onFirebaseDataCustomer.onFailure(new Exception("Gagal menghapus order"));
        }

        if (isProduction) {
            firestoreApp.delete(collectionReference, customer.getId(), new OnDatabaseFirebaseListener() {
                @Override
                public void onSuccess(String message, String id) {
                    super.onSuccess(message, id);
                    onFirebaseDataCustomer.onSuccess();
                }

                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    onFirebaseDataCustomer.onFailure(e);
                }
            });
        } else {
            virtualDataApp.delete(customer.getId());
            onFirebaseDataCustomer.onSuccess();
        }
    }

    public void getData(String idLaundry, OnNotifyDataCustomer onNotifyDataCustomer) {
//        DebugLog.RULES("Memanggil Query", LOG);
        //Pemanggilan Alamat
//        Query collectionReference = CollectionFirestore.USERS().whereArrayContains("notification_ids", "iniRapiLaundryW1IpUu");
        CollectionReference collectionReference = CollectionFirestore.USERS();

        if (isProduction) {
            firestoreApp.querySuscribe(collectionReference, idLaundry, new OnDatabaseFirebaseListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    super.onFailure(e);
                    onNotifyDataCustomer.onFailure(e);
                }

                @Override
                public void getDataNew(QueryDocumentSnapshot data) {
                    super.getDataNew(data);
                    onNotifyDataCustomer.onNotify(data.toObject(Customer.class));
                }

                @Override
                public void getDataModify(QueryDocumentSnapshot data) {
                    super.getDataModify(data);
                    onNotifyDataCustomer.onNotifyModify(data.toObject(Customer.class));
                }

                @Override
                public void getDataRemove(QueryDocumentSnapshot data) {
                    super.getDataRemove(data);
                    onNotifyDataCustomer.onNotifyRemove(data.toObject(Customer.class));
                }
            });
//            collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
//                @Override
//                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                    if (error != null) {
//                        DebugLog.RULES("Terjadi kesalahan " + error.getMessage(), LOG);
//                        onNotifyDataCustomer.onFailure(new Exception("Terjadi kesalahan dalam event server"));
//                        return;
//                    }
//
//                    if (value != null) {
//                        DebugLog.RULES("Sedang memuat notify : " + value.getDocumentChanges().size() + " data", LOG);
//                        for (DocumentChange dc : value.getDocumentChanges()) {
//                            Customer customer = dc.getDocument().toObject(Customer.class);
//                            String source = value.getMetadata().isFromCache() ?
//                                    "local cache" : "server";
//
//                            switch (dc.getType()) {
//                                case ADDED:
//                                    onNotifyDataCustomer.onNotify(customer);
//                                    DebugLog.RULES("---> Data baru: " + dc.getDocument().getData(), LOG);
//                                    break;
//                                case MODIFIED:
//                                    onNotifyDataCustomer.onNotifyModify(customer);
//                                    DebugLog.RULES("---> Data Modified: " + dc.getDocument().getData(), LOG);
//                                    break;
//                                case REMOVED:
//                                    onNotifyDataCustomer.onNotifyRemove(customer);
//                                    DebugLog.RULES("---> Data Removed: " + dc.getDocument().getData(), LOG);
//                                    break;
//                            }
//
//                            DebugLog.RULES("^^^ Data diambil dari " + source + " ^^^", LOG);
//                        }
//                    }
//                }
//            });
//            firestoreApp.dataRealTime(collectionReference, new OnDatabaseFirebaseListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    super.onFailure(e);
//                    onNotifyDataCustomer.onFailure(e);
//                }
//
//                @Override
//                public void getDataNew(QueryDocumentSnapshot data) {
//                    super.getDataNew(data);
//                    Customer customer = data.toObject(Customer.class);
//                    onNotifyDataCustomer.onNotify(customer);
//                }
//
//                @Override
//                public void getDataModify(QueryDocumentSnapshot data) {
//                    super.getDataModify(data);
//                    Customer customer = data.toObject(Customer.class);
//                    onNotifyDataCustomer.onNotifyModify(customer);
//                }
//
//                @Override
//                public void getDataRemove(QueryDocumentSnapshot data) {
//                    super.getDataRemove(data);
//                    Customer customer = data.toObject(Customer.class);
//                    onNotifyDataCustomer.onNotifyRemove(customer);
//                }
//            });
        } else {
            virtualDataApp.read();
        }
    }

    public void searchByPhone(String phone, OnQueryDataCustomer onQueryDataCustomer) {
        if (phone.isEmpty()) {
            DebugLog.RULES("Phone kosong, pencarian dibatalkan", LOG);
            onQueryDataCustomer.onFailure(new Exception("Phone kosong, pencarian dibatalkan"));
            return;
        }

        Query query = CollectionFirestore.USERS().whereEqualTo("phone", phone);

        firestoreApp.search(query, new OnDatabaseFirebaseListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                super.onFailure(e);
                onQueryDataCustomer.onFailure(e);
                DebugLog.RULES("Gagal mencari - "+e.getMessage(), LOG);

            }

            @Override
            public void getAllData(QuerySnapshot data) {
                super.getAllData(data);
                List<Customer> list = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : data) {
                    Customer customer = documentSnapshot.toObject(Customer.class);
                    list.add(customer);
                    DebugLog.RULES("Server Data = "+customer, LOG);
                }
                onQueryDataCustomer.onSuccess(list);
            }
        });
    }

//    public void search(String field,String phone, OnSearchListener listener) {
//        collection.whereEqualTo(field, phone).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                resultSearch.clear();
//                for (int i = 0; i < queryDocumentSnapshots.size(); i++) {
//                    Customer customer = queryDocumentSnapshots.getDocumentChanges().get(i).getDocument().toObject(Customer.class);
//                    resultSearch.add(customer);
//                }
//                listener.onSuccess(resultSearch);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                listener.onFailure(e.getMessage());
//            }
//        });
//    }

    /**/
//    @Deprecated
//    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//    @Deprecated
//    private static final CollectionReference collection = db.collection("Users");
//
//    @Deprecated
//    private final List<Customer> resultSearch = new ArrayList<>();
//
//
//    @Deprecated
//    public void addCustomer(@NonNull Customer customer, OnAddCustomerListener listener) {
//        //Generate ID from Firestore
//        String generateID = collection.document().getId();
//
//        //Pengecekan format number
//        if (PhoneNumberUtils.isValidPhoneNumber(customer.getPhone())) {
//            customer.setPhone(PhoneNumberUtils.formatPhoneNumber(customer.getPhone()));
//        } else {
//            listener.onFailure("Nomor tidak valid");
//            return;
//        }
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("id", generateID);
//        map.put("name", customer.getName());
//        map.put("username", customer.getUsername());
//        map.put("phone", customer.getPhone());
//        map.put("address", customer.getAddress());
//        map.put("email", customer.getEmail());
//        map.put("idLaundry", customer.getIdLaundry());
//        map.put("idBranch", customer.getIdBranch());
//        map.put("role", customer.getRole());
//        map.put("uid", customer.getUid());
//        map.put("photo", customer.getPhoto());
//        map.put("status", customer.getStatus());
//        map.put("registrationToken", customer.getRegistrationToken());
//        map.put("whatsapp_verified", customer.isWhatsapp_verified());
//        map.put("verified", customer.isVerified());
//        map.put("isAdmin", customer.isAdmin());
//        map.put("createdAt", customer.getCreatedAt());
//        map.put("updatedAt", customer.getUpdatedAt());
//        map.put("status_user", customer.isStatus_user());
//        map.put("notification_ids", customer.getNotification_ids());
//        map.put("order_ids", customer.getOrder_ids());
//        map.put("payment_ids", customer.getPayment_ids());
//        map.put("laundry_ids", customer.getLaundry_ids());
//        map.put("service_ids", customer.getService_ids());
//        map.put("employee_ids", customer.getEmployee_ids());
//        map.put("branch_ids", customer.getBranch_ids());
//
//        collection.whereEqualTo("phone", customer.getPhone()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if (queryDocumentSnapshots.isEmpty()) {
//                    collection.document(generateID).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            listener.onSuccess(customer);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            listener.onFailure(e.getMessage());
//                        }
//                    });
//                } else {
//                    listener.onFailure("Nomor telephone telah digunakan");
//                }
//            }
//        });
//    }
//
//    @Deprecated
//    public interface OnAddCustomerListener {
//        void onSuccess(Customer customer);
//
//        void onFailure(String error);
//    }
//
//
//
//    @Deprecated
//    public interface OnSearchListener {
//        void onSuccess(List<Customer> customers);
//
//        void onFailure(String error);
//    }
//
//    @Deprecated
//    public Task<QuerySnapshot> getMyCustomer(String myIdLaundry) {
//        System.out.print("Memanggil getAllCustomer (FirestoreCustomerData) -> ");
//        return collection.whereArrayContains("notification_ids", myIdLaundry).get(Source.CACHE);
//    }
//
//    @Deprecated
//    public Task<QuerySnapshot> getCustomerGlobal() {
//        return collection.get();
//    }
//
//    @Deprecated
//    public static void refreshData(OnrefreshDataListener dataListener) {
////        db.setFirestoreSettings(settings);
//
//        collection.addSnapshotListener((value, e) -> {
//            if (e != null) {
//                Log.w("TAG", "listen:error", e);
//                return;
//            }
//
//            for (DocumentChange dc : value.getDocumentChanges()) {
//                System.out.println("Ini dia : " + dc.getType());
//                Customer customer = dc.getDocument().toObject(Customer.class);
//
//                switch (dc.getType()) {
//                    case ADDED:
//                        Log.d("TAG", "New user: " + dc.getDocument().getData());
//                        dataListener.onNew(customer);
//                        break;
//                    case MODIFIED:
//                        Log.d("TAG", "Modified user: " + dc.getDocument().getData());
//                        dataListener.onModified(customer);
//                        break;
//                    case REMOVED:
//                        Log.d("TAG", "Removed user: " + dc.getDocument().getData());
//                        dataListener.onRemove(customer);
//                        break;
//                }
//                String source = value.getMetadata().isFromCache() ?
//                        "local cache" : "server";
//
//                Log.d(TAG, "Data fetched from " + source);
//            }
//        });
//    }
//
//    @Deprecated
//    public interface OnrefreshDataListener {
//        void onNew(Customer customer);
//
//        void onModified(Customer customer);
//
//        void onRemove(Customer customer);
//    }

}
