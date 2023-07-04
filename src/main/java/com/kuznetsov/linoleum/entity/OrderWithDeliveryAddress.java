package com.kuznetsov.linoleum.entity;

import java.time.LocalDateTime;

public class OrderWithDeliveryAddress extends Order{
    private DeliveryAddress deliveryAddress;

    public OrderWithDeliveryAddress(){

    }

    public OrderWithDeliveryAddress(Integer id, LocalDateTime creatingDate, OrderStatus status, OrderTransporting transporting, LocalDateTime transportingDate, Integer cost, Integer apartmentNum, User user, Linoleum linoleum, DeliveryAddress deliveryAddress) {
        super(id, creatingDate, status, transporting, transportingDate, cost, apartmentNum, user, linoleum);
        this.deliveryAddress = deliveryAddress;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "OrderWithDeliveryAddress{" +
                "deliveryAddress=" + deliveryAddress +
                '}';
    }
}
