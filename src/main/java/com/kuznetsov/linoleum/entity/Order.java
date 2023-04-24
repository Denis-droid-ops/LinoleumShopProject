package com.kuznetsov.linoleum.entity;

import java.time.LocalDateTime;

public class Order {
    private Integer id;
    private LocalDateTime creatingDate;
    private String status;
    private String transporting;
    private LocalDateTime transportingDate;
    private Integer cost;
    private User user;
    private Linoleum linoleum;
    private Address address;

    public Order() {
    }

    public Order(Integer id, LocalDateTime creatingDate, String status, String transporting, LocalDateTime transportingDate, Integer cost, User user, Linoleum linoleum, Address address) {
        this.id = id;
        this.creatingDate = creatingDate;
        this.status = status;
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.user = user;
        this.linoleum = linoleum;
        this.address = address;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransporting() {
        return transporting;
    }

    public void setTransporting(String transporting) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", creatingDate=" + creatingDate +
                ", status='" + status + '\'' +
                ", transporting='" + transporting + '\'' +
                ", transportingDate=" + transportingDate +
                ", cost=" + cost +
                ", user=" + user +
                ", linoleum=" + linoleum +
                ", address=" + address +
                '}';
    }
}
