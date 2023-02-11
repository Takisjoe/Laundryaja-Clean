package com.takisjoeapp.laundryaja.database.firebase;

import androidx.annotation.NonNull;

import com.takisjoeapp.laundryaja.util.debug.ModeService;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;

public class DatabaseFirestoreTest extends TestCase {
    FirestoreApp firestore;

    public void setUp() throws Exception {
        super.setUp();
//        firestore = new DatabaseFirestoreApp(ModeService.DEBUG);
    }

    public void testCreate() {
        FirestoreApp firestore = new DatabaseFirestoreApp();

        Map<String, Object> map = new HashMap<>();

//        firestore.create(CollectionFirestore.USERS(ModeService.DEBUG), "", map, new OnDatabaseFirebaseListener() {
//            @Override
//            void onSuccess() {
//                super.onSuccess();
//            }
//
//            @Override
//            void onFailure(@NonNull Exception e) {
//                super.onFailure(e);
//            }
//        });

    }
}