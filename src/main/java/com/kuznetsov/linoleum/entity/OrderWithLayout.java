package com.kuznetsov.linoleum.entity;

import java.time.LocalDateTime;

public class OrderWithLayout extends Order{
    private Layout layout;

    public OrderWithLayout(){

    }

    public OrderWithLayout(Integer id, LocalDateTime creatingDate, OrderStatus status, OrderTransporting transporting, LocalDateTime transportingDate, Integer cost, Integer apartmentNum, User user, Linoleum linoleum, Layout layout) {
        super(id, creatingDate, status, transporting, transportingDate, cost, apartmentNum, user, linoleum);
        this.layout = layout;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "OrderWithLayout{" +
                "layout=" + layout +
                '}';
    }
}
