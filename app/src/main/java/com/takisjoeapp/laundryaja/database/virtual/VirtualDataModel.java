package com.takisjoeapp.laundryaja.database.virtual;

import java.util.Map;

public class VirtualDataModel {
    private String id;
    private String text;
    private Map<String,Object> map;

    public VirtualDataModel() {
    }

    public VirtualDataModel(String id, String text, Map<String, Object> map) {
        this.id = id;
        this.text = text;
        this.map = map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String,Object> getObject() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
