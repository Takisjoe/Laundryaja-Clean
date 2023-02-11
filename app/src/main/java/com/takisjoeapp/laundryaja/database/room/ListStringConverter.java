package com.takisjoeapp.laundryaja.database.room;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class ListStringConverter {
    @TypeConverter
    public String fromList(List<String> list) {
        if (list == null) return null;
        return TextUtils.join(";", list);
    }

    @TypeConverter
    public List<String> toList(String value) {
        if (value == null) return null;
        return Arrays.asList(value.split(";"));
    }
}

