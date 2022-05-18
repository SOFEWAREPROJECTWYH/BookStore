package com.lut.wyh.BookStore.event;

import com.lut.wyh.BookStore.entity.Inventory;

import java.util.List;

public class InventoryEvent {
    private List<Inventory> inventoryList;

    public InventoryEvent(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }

    public List<Inventory> getInventoryList() {
        return inventoryList;
    }

    public void setInventoryList(List<Inventory> inventoryList) {
        this.inventoryList = inventoryList;
    }
}
