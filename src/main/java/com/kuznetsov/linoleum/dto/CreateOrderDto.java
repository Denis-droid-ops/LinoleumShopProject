package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateOrderDto {
    private final String transporting;
    private final String transportingDate;
    private final String cost;
    private final String apartmentNum;
    private final String userId;
    private final String linoleumId;

    private final String layoutId;
    private final CreateLayoutDto createLayoutDto;
    private final CreateDeliveryAddressDto createDeliveryAddressDto;

    public CreateOrderDto(String transporting, String transportingDate, String cost, String apartmentNum, String userId, String linoleumId) {
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
        this.linoleumId = linoleumId;
        this.layoutId = null;
        this.createLayoutDto = null;
        this.createDeliveryAddressDto = null;
    }

    public CreateOrderDto(String transporting, String transportingDate, String cost, String apartmentNum, String userId, String linoleumId, String layoutId) {
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
        this.linoleumId = linoleumId;
        this.layoutId = layoutId;
        this.createLayoutDto = null;
        this.createDeliveryAddressDto = null;
    }

    public CreateOrderDto(String transporting, String transportingDate, String cost, String apartmentNum, String userId, String linoleumId, CreateLayoutDto createLayoutDto) {
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
        this.linoleumId = linoleumId;
        this.createLayoutDto = createLayoutDto;
        this.layoutId = null;
        this.createDeliveryAddressDto = null;
    }

    public CreateOrderDto(String transporting, String transportingDate, String cost, String apartmentNum, String userId, String linoleumId, CreateDeliveryAddressDto createDeliveryAddressDto) {
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
        this.linoleumId = linoleumId;
        this.createDeliveryAddressDto = createDeliveryAddressDto;
        this.layoutId = null;
        this.createLayoutDto = null;
    }

    public String getTransporting() {
        return transporting;
    }

    public String getTransportingDate() {
        return transportingDate;
    }

    public String getCost() {
        return cost;
    }

    public String getApartmentNum() {
        return apartmentNum;
    }

    public String getUserId() {
        return userId;
    }

    public String getLinoleumId() {
        return linoleumId;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public CreateLayoutDto getCreateLayoutDto() {
        return createLayoutDto;
    }

    public CreateDeliveryAddressDto getCreateDeliveryAddressDto() {
        return createDeliveryAddressDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateOrderDto)) return false;
        CreateOrderDto that = (CreateOrderDto) o;
        return Objects.equals(transporting, that.transporting) && Objects.equals(transportingDate, that.transportingDate) && Objects.equals(cost, that.cost) && Objects.equals(apartmentNum, that.apartmentNum) && Objects.equals(userId, that.userId) && Objects.equals(linoleumId, that.linoleumId) && Objects.equals(layoutId, that.layoutId) && Objects.equals(createLayoutDto, that.createLayoutDto) && Objects.equals(createDeliveryAddressDto, that.createDeliveryAddressDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transporting, transportingDate, cost, apartmentNum, userId, linoleumId, layoutId, createLayoutDto, createDeliveryAddressDto);
    }

    @Override
    public String toString() {
        return "CreateOrderDto{" +
                "transporting='" + transporting + '\'' +
                ", transportingDate='" + transportingDate + '\'' +
                ", cost='" + cost + '\'' +
                ", apartmentNum='" + apartmentNum + '\'' +
                ", userId='" + userId + '\'' +
                ", linoleumId='" + linoleumId + '\'' +
                ", layoutId='" + layoutId + '\'' +
                ", createLayoutDto=" + createLayoutDto +
                ", createDeliveryAddressDto=" + createDeliveryAddressDto +
                '}';
    }
}
