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

    public CreateOrderDto(String transporting, String transportingDate, String cost, String apartmentNum, String userId, String linoleumId, String layoutId, CreateLayoutDto createLayoutDto, CreateDeliveryAddressDto createDeliveryAddressDto) {
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.userId = userId;
        this.linoleumId = linoleumId;
        this.layoutId = layoutId;
        this.createLayoutDto = createLayoutDto;
        this.createDeliveryAddressDto = createDeliveryAddressDto;
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

    public static class CreateOrderDtoBuilder{
        private String transporting;
        private String transportingDate;
        private String cost;
        private String apartmentNum;
        private String userId;
        private String linoleumId;

        private String layoutId;
        private CreateLayoutDto createLayoutDto;
        private CreateDeliveryAddressDto createDeliveryAddressDto;

        public CreateOrderDtoBuilder(){}

        public CreateOrderDtoBuilder transporting(String transporting){
            this.transporting = transporting;
            return this;
        }

        public CreateOrderDtoBuilder transportingDate(String transportingDate){
            this.transportingDate = transportingDate;
            return this;
        }

        public CreateOrderDtoBuilder cost(String cost){
            this.cost = cost;
            return this;
        }

        public CreateOrderDtoBuilder apartmentNum(String apartmentNum){
            this.apartmentNum = apartmentNum;
            return this;
        }

        public CreateOrderDtoBuilder userId(String userId){
            this.userId = userId;
            return this;
        }

        public CreateOrderDtoBuilder linoleumId(String linoleumId){
            this.linoleumId = linoleumId;
            return this;
        }

        public CreateOrderDtoBuilder layoutId(String layoutId){
            this.layoutId = layoutId;
            return this;
        }

        public CreateOrderDtoBuilder createLayoutDto(CreateLayoutDto createLayoutDto){
            this.createLayoutDto = createLayoutDto;
            return this;
        }

        public CreateOrderDtoBuilder createDeliveryAddressDto(CreateDeliveryAddressDto createDeliveryAddressDto){
            this.createDeliveryAddressDto = createDeliveryAddressDto;
            return this;
        }

        public CreateOrderDto build(){
            return new CreateOrderDto(this.transporting,this.transportingDate,this.cost
            ,this.apartmentNum,this.userId,this.linoleumId,this.layoutId
            ,this.createLayoutDto,this.createDeliveryAddressDto);
        }

        @Override
        public String toString() {
            return "CreateOrderDtoBuilder{" +
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

    public static CreateOrderDtoBuilder builder(){
        return new CreateOrderDtoBuilder();
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
