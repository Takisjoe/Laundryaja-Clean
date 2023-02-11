package com.takisjoeapp.laundryaja.database.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;

public abstract class OnDatabaseFirebaseListener {
    private final String LOG = "OnDatabaseFirebaseListener";

    public void onSuccess(String message, String id) {
        DebugLog.RULES("Berhasil", LOG);
    }

    public void onFailure(@NonNull Exception e) {
        DebugLog.RULES("Gagal - " + e.getMessage(), LOG);
    }

    public void getAllData(QuerySnapshot data) {
        DebugLog.RULES("Notify : Create data baru", LOG);
    }
    public void getDataNew(QueryDocumentSnapshot data) {
        DebugLog.RULES("Notify : Create data baru", LOG);
    }

    public void getDataModify(QueryDocumentSnapshot data) {
        DebugLog.RULES("Notify : Update data", LOG);
    }

    public void getDataRemove(QueryDocumentSnapshot data) {
        DebugLog.RULES("Notify : Delete data", LOG);
    }
}
