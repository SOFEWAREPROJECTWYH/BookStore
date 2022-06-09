package com.lut.wyh.BookStore.event;

public class CollectDeleteEvent {
    private Integer deleteCondition;

    public CollectDeleteEvent(Integer deleteCondition) {
        this.deleteCondition = deleteCondition;
    }

    public Integer getDeleteCondition() {
        return deleteCondition;
    }

    public void setDeleteCondition(Integer deleteCondition) {
        this.deleteCondition = deleteCondition;
    }

    @Override
    public String toString() {
        return "CollectDeleteEvent{" +
                "deleteCondition=" + deleteCondition +
                '}';
    }
}
