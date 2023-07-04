package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class DeliveryAddress {
    private Integer id;
    private String dCity;
    private String dStreet;
    private String dHomeNum;

    public DeliveryAddress(Integer id, String dCity, String dStreet, String dHomeNum) {
        this.id = id;
        this.dCity = dCity;
        this.dStreet = dStreet;
        this.dHomeNum = dHomeNum;
    }

    public DeliveryAddress(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdCity() {
        return dCity;
    }

    public void setdCity(String dCity) {
        this.dCity = dCity;
    }

    public String getdStreet() {
        return dStreet;
    }

    public void setdStreet(String dStreet) {
        this.dStreet = dStreet;
    }

    public String getdHomeNum() {
        return dHomeNum;
    }

    public void setdHomeNum(String dHomeNum) {
        this.dHomeNum = dHomeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryAddress)) return false;
        DeliveryAddress that = (DeliveryAddress) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "id=" + id +
                ", dCity='" + dCity + '\'' +
                ", dStreet='" + dStreet + '\'' +
                ", dHomeNum='" + dHomeNum + '\'' +
                '}';
    }
}
