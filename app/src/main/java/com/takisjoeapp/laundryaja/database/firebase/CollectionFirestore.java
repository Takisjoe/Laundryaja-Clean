package com.takisjoeapp.laundryaja.database.firebase;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.takisjoeapp.laundryaja.feature.user.domain.entitas.User;

public class CollectionFirestore {
    private static final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private static final FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build();



    public static CollectionReference USERS() {
        return firestore.collection("Users");
    }

    public static CollectionReference ORDERS(User user) {
        return firestore.collection("Laundry").document(user.getIdLaundry()).collection("Branch").document(user.getIdBranch()).collection("Orders");
    }
}
