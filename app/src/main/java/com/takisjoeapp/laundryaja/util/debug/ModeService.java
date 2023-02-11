package com.takisjoeapp.laundryaja.util.debug;

import android.content.Context;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.Contract;

public enum ModeService {
    ROOT("ROOT", "Mengambil langsung"),
    RULES("RULES", "Mengambil sesuai aturan"),
    DEBUG("DEBUG", "Hanya untuk tester/uji coba", null);


    private String mode;
    private String desc;
    private Context context;

    ModeService(String mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    ModeService(String mode, String desc, Context context) {
        this.mode = mode;
        this.desc = desc;
        this.context = context;
    }

    public String getMode() {
        return mode;
    }

    @NonNull
    @Contract(pure = true)
    public String getDesc() {
        return "Mode Service - " + desc;
    }

    public Context getContext() {
        return context;
    }

    public ModeService setContext(Context context) {
        this.context = context;
        return this;
    }
}
