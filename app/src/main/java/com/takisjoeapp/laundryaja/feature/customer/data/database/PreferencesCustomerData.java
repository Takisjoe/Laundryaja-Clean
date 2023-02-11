package com.takisjoeapp.laundryaja.feature.customer.data.database;

import android.app.Application;

public class PreferencesCustomerData {
    private Application application;

    public PreferencesCustomerData(Application application) {
        this.application = application;
    }

    //TODO: Buat penyimpanan data lokal untuk menyimpan customer terakhir yang dikelola
}
