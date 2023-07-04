package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateDeliveryAddressDto {
    private final String dCity;
    private final String dStreet;
    private final String dHomeNum;

    public CreateDeliveryAddressDto(String dCity, String dStreet, String dHomeNum) {
        this.dCity = dCity;
        this.dStreet = dStreet;
        this.dHomeNum = dHomeNum;
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
        if (!(o instanceof CreateDeliveryAddressDto)) return false;
        CreateDeliveryAddressDto that = (CreateDeliveryAddressDto) o;
        return Objects.equals(dCity, that.dCity) && Objects.equals(dStreet, that.dStreet) && Objects.equals(dHomeNum, that.dHomeNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dCity, dStreet, dHomeNum);
    }

    @Override
    public String toString() {
        return "CreateDeliveryAddressDto{" +
                "dCity='" + dCity + '\'' +
                ", dStreet='" + dStreet + '\'' +
                ", dHomeNum='" + dHomeNum + '\'' +
                '}';
    }
}
