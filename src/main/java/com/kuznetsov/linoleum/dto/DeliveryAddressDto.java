package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class DeliveryAddressDto {
    private final Integer id;
    private final String dCity;
    private final String dStreet;
    private final String dHomeNum;

    public DeliveryAddressDto(Integer id, String dCity, String dStreet, String dHomeNum) {
        this.id = id;
        this.dCity = dCity;
        this.dStreet = dStreet;
        this.dHomeNum = dHomeNum;
    }

    public Integer getId() {
        return id;
    }

    public String getdCity() {
        return dCity;
    }

    public String getdStreet() {
        return dStreet;
    }

    public String getdHomeNum() {
        return dHomeNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryAddressDto)) return false;
        DeliveryAddressDto that = (DeliveryAddressDto) o;
        return Objects.equals(id, that.id) && Objects.equals(dCity, that.dCity) && Objects.equals(dStreet, that.dStreet) && Objects.equals(dHomeNum, that.dHomeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dCity, dStreet, dHomeNum);
    }

    @Override
    public String toString() {
        return "DeliveryAddressDto{" +
                "id=" + id +
                ", dCity='" + dCity + '\'' +
                ", dStreet='" + dStreet + '\'' +
                ", dHomeNum='" + dHomeNum + '\'' +
                '}';
    }
}
