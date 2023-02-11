package com.takisjoeapp.laundryaja.util.address;

public class Address {
    private String id;
    private String nama;

    public Address() {
    }

    public Address(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
