package com.lut.wyh.BookStore.event;

public class InsertShopTroEvent {
    private Integer id;

    public InsertShopTroEvent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
