package com.kuznetsov.linoleum.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private Integer id;
    private LocalDateTime creatingDate;
    private OrderStatus status;
    private OrderTransporting transporting;
    private LocalDateTime transportingDate;
    private Integer cost;
    private Integer apartmentNum;
    private User user;
    private Linoleum linoleum;
    private List<Fragment> fragments;

    public Order() {
    }

    public Order(Integer id, LocalDateTime creatingDate, OrderStatus status, OrderTransporting transporting, LocalDateTime transportingDate,
                 Integer cost, Integer apartmentNum, User user, Linoleum linoleum, List<Fragment> fragments) {
        this.id = id;
        this.creatingDate = creatingDate;
        this.status = status;
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.user = user;
        this.linoleum = linoleum;
        this.fragments = fragments;
    }

    public Order(Integer id, LocalDateTime creatingDate, OrderStatus status, OrderTransporting transporting, LocalDateTime transportingDate,
                 Integer cost, Integer apartmentNum, User user, Linoleum linoleum) {
        this.id = id;
        this.creatingDate = creatingDate;
        this.status = status;
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.user = user;
        this.linoleum = linoleum;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(LocalDateTime creatingDate) {
        this.creatingDate = creatingDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderTransporting getTransporting() {
        return transporting;
    }

    public void setTransporting(OrderTransporting transporting) {
        this.transporting = transporting;
    }

    public LocalDateTime getTransportingDate() {
        return transportingDate;
    }

    public void setTransportingDate(LocalDateTime transportingDate) {
        this.transportingDate = transportingDate;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(Integer apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Linoleum getLinoleum() {
        return linoleum;
    }

    public void setLinoleum(Linoleum linoleum) {
        this.linoleum = linoleum;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creatingDate=" + creatingDate +
                ", status=" + status +
                ", transporting=" + transporting +
                ", transportingDate=" + transportingDate +
                ", cost=" + cost +
                ", apartmentNum=" + apartmentNum +
                ", user=" + user +
                ", linoleum=" + linoleum +
                ", fragments=" + fragments +
                '}';
    }
}
