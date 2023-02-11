package com.takisjoeapp.laundryaja.feature.order.data.database.firebase;

public interface OnFirebaseDataOrder {
    void onSuccess();

    void onFailure(String error);
}
