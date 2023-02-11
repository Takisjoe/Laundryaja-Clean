package com.takisjoeapp.laundryaja.database.virtual;

import java.util.HashMap;
import java.util.Map;

public class VirtualDataBuilder {
    private String id ="ID-0";
    private String text = "";
    private Map<String,Object> object = new HashMap<>();

    public VirtualDataModel build() {
        return new VirtualDataModel(id,text,object);
    }

    public VirtualDataBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public VirtualDataBuilder setObject(Map<String, Object> object) {
        this.object = object;
        return this;
    }
}
