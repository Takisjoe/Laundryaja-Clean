package com.takisjoeapp.laundryaja.feature.order.domain.entitas;

public class Note {
    private String product;
    private int count_item;
    private int price_item;
    private int total;
    private String note;
    private long createAt;

    public Note() {
    }

    public Note(String product, int count_item, int price_item, int total, String note, long createAt) {
        this.product = product;
        this.count_item = count_item;
        this.price_item = price_item;
        this.total = total;
        this.note = note;
        this.createAt = createAt;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount_item() {
        return count_item;
    }

    public void setCount_item(int count_item) {
        this.count_item = count_item;
    }

    public int getPrice_item() {
        return price_item;
    }

    public void setPrice_item(int price_item) {
        this.price_item = price_item;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
