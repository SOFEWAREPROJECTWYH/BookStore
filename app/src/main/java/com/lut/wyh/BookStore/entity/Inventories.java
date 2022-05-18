package com.lut.wyh.BookStore.entity;

import java.util.List;

public class Inventories {
    private List<Inventory> inventoryList;

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    @Override
    public String toString() {
        return "Inventories{" +
                "inventoryList=" + inventoryList +
                '}';
    }
}
