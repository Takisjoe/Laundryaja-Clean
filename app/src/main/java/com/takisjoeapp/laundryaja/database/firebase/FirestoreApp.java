package com.takisjoeapp.laundryaja.database.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.takisjoeapp.laundryaja.feature.customer.data.database.firebase.OnQueryDataCustomer;

import java.util.Map;

public interface FirestoreApp {

    void querySuscribe(CollectionReference collectionReference, String idLaundry, OnDatabaseFirebaseListener onDatabaseFirebaseListener);

    void dataRealTime(CollectionReference collectionReference, OnDatabaseFirebaseListener onDatabaseFirebaseListener); //Testing OK

    void create(CollectionReference collectionReference, String newId, Map<String, Object> map, OnDatabaseFirebaseListener onDatabaseFirebaseListener); //Testing OK

    void update(CollectionReference collectionReference, String idUpdate, Map<String, Object> map, OnDatabaseFirebaseListener onDatabaseFirebaseListener); //Testing OK

    void delete(CollectionReference collectionReference, String idDelete, OnDatabaseFirebaseListener onDatabaseFirebaseListener); //Testing OK

    void search(Query query, OnDatabaseFirebaseListener onDatabaseFirebaseListener);
}
