package com.takisjoeapp.laundryaja.database.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.takisjoeapp.laundryaja.util.debug.DebugLog;

import java.util.Map;

public class DatabaseFirestoreApp implements FirestoreApp {
    private final String LOG = "DatabaseFirestoreApp";

    @Override
    public void querySuscribe(@NonNull CollectionReference collectionReference, String idLaundry, OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        collectionReference.whereArrayContains("notification_ids", idLaundry).addSnapshotListener((value, error) -> {
            if (error != null) {
                DebugLog.RULES("Terjadi kesalahan " + error.getMessage(), LOG);
                onDatabaseFirebaseListener.onFailure(new Exception("Terjadi kesalahan dalam event server - "+error.getMessage()));
                return;
            }

            if (value != null) {
                DebugLog.RULES("Sedang memuat notify : " + value.getDocumentChanges().size() + " data", LOG);
                for (DocumentChange dc : value.getDocumentChanges()) {
                    String source = value.getMetadata().isFromCache() ?
                            "local cache" : "server";

                    switch (dc.getType()) {
                        case ADDED:
                            onDatabaseFirebaseListener.getDataNew(dc.getDocument());
                            DebugLog.RULES("---> Data baru: " + dc.getDocument().getData(), LOG);
                            break;
                        case MODIFIED:
                            onDatabaseFirebaseListener.getDataModify(dc.getDocument());
                            DebugLog.RULES("---> Data Modified: " + dc.getDocument().getData(), LOG);
                            break;
                        case REMOVED:
                            onDatabaseFirebaseListener.getDataRemove(dc.getDocument());
                            DebugLog.RULES("---> Data Removed: " + dc.getDocument().getData(), LOG);
                            break;
                    }

                    DebugLog.RULES("^^^ Data Suscribe diambil dari " + source + " ^^^", LOG);
                }
            }
        });
    }

    @Override
    public void dataRealTime(@NonNull CollectionReference collectionReference, OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        DebugLog.RULES("-------------------------------------------", LOG);
        collectionReference.addSnapshotListener((value, error) -> {
            if (error != null) {
                DebugLog.RULES("Terjadi kesalahan " + error.getMessage(), LOG);
                onDatabaseFirebaseListener.onFailure(new Exception("Terjadi kesalahan dalam event server"));
                return;
            }

            if (value != null) {
                DebugLog.RULES("Sedang memuat notify : " + value.getDocumentChanges().size() + " data", LOG);
                for (DocumentChange dc : value.getDocumentChanges()) {
//              Order order = dc.getDocument().toObject(Order.class);
                    String source = value.getMetadata().isFromCache() ?
                            "local cache" : "server";

                    switch (dc.getType()) {
                        case ADDED:
                            onDatabaseFirebaseListener.getDataNew(dc.getDocument());
                            DebugLog.RULES("---> Data baru: " + dc.getDocument().getData(), LOG);
                            break;
                        case MODIFIED:
                            onDatabaseFirebaseListener.getDataModify(dc.getDocument());
                            DebugLog.RULES("---> Data Modified: " + dc.getDocument().getData(), LOG);
                            break;
                        case REMOVED:
                            onDatabaseFirebaseListener.getDataRemove(dc.getDocument());
                            DebugLog.RULES("---> Data Removed: " + dc.getDocument().getData(), LOG);
                            break;
                    }

                    DebugLog.RULES("^^^ Data diambil dari " + source + " ^^^", LOG);
                }
            }
        });
    }

    @Override
    public void create(CollectionReference collectionReference, String newId, Map<String, Object> map, OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        DebugLog.RULES("-------------------------------------------", LOG);
        DebugLog.RULES("Sedang menyiapkan create", LOG);

        if (collectionReference == null) {
            DebugLog.RULES("Gagal menemukan alamat server 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server"));
            return;
        }
        if (newId.isEmpty()) {
            DebugLog.RULES("Gagal menemukan alamat server untuk create 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server, alamat order tidak ditemukan"));
            return;
        }

        collectionReference.document(newId).set(map).addOnSuccessListener(unused -> {
            onDatabaseFirebaseListener.onSuccess("Berhasil membuat " + newId + " dari server", newId);
            DebugLog.RULES("Create berhasil " + newId, LOG);
        }).addOnFailureListener(e -> {
            onDatabaseFirebaseListener.onFailure(e);
            DebugLog.RULES("Create gagal " + e.getMessage(), LOG);
        });

    }

    @Override
    public void update(CollectionReference collectionReference, String idUpdate, Map<String, Object> map, OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        DebugLog.RULES("-------------------------------------------", LOG);
        DebugLog.RULES("Sedang menyiapkan update", LOG);

        if (collectionReference == null) {
            DebugLog.RULES("Gagal menemukan alamat server untuk update 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server"));
            return;
        }

        if (idUpdate.isEmpty()) {
            DebugLog.RULES("Gagal menemukan alamat server untuk update 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server, alamat order tidak ditemukan"));
            return;
        }

        collectionReference.document(idUpdate).update(map).addOnSuccessListener(unused -> {
            onDatabaseFirebaseListener.onSuccess("Berhasil merubah " + idUpdate + " dari server", idUpdate);
            DebugLog.RULES("Update berhasil ", LOG);

        }).addOnFailureListener(e -> {
            onDatabaseFirebaseListener.onFailure(e);
            DebugLog.RULES("Update gagal " + e.getMessage(), LOG);

        });

    }

    @Override
    public void delete(CollectionReference collectionReference, String idDelete, OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        DebugLog.RULES("-------------------------------------------", LOG);
        DebugLog.RULES("Sedang menyiapkan delete", LOG);

        if (collectionReference == null) {
            DebugLog.RULES("Gagal menemukan alamat server untuk delete 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server"));
            return;
        }
        if (idDelete.isEmpty()) {
            DebugLog.RULES("Gagal menemukan alamat server untuk delete 'collectionReference == null'", LOG);
            onDatabaseFirebaseListener.onFailure(new Exception("Gagal terhubung ke server, alamat order tidak ditemukan"));
            return;
        }

        collectionReference.document(idDelete).delete().addOnSuccessListener(unused -> {
            DebugLog.RULES("Berhasil menghapus " + idDelete + " dari server", LOG);
            onDatabaseFirebaseListener.onSuccess("Berhasil menghapus " + idDelete + " dari server", idDelete);
        }).addOnFailureListener(e -> {
            DebugLog.RULES("Gagal menghapus " + idDelete + " dari server", LOG);
            onDatabaseFirebaseListener.onFailure(e);
        });
    }

    @Override
    public void search(@NonNull Query query, final OnDatabaseFirebaseListener onDatabaseFirebaseListener) {
        query.addSnapshotListener((value, error) -> {
            if (error != null) {
                onDatabaseFirebaseListener.onFailure(error);
                return;
            }
            if (value != null) {
                onDatabaseFirebaseListener.getAllData(value);
            }
        });
    }



}
