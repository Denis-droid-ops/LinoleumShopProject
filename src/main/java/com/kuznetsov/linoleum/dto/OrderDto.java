package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.*;

import java.time.LocalDateTime;
import java.util.Objects;

public final class OrderDto {//Dto for reading
    private final Integer id;
    private final LocalDateTime creatingDate;
    private final OrderStatus status;
    private final OrderTransporting transporting;
    private final LocalDateTime transportingDate;
    private final Integer cost;
    private final Integer apartmentNum;
    private final User user;
    private final Linoleum linoleum;

    private final Layout layout;
    private final DeliveryAddress deliveryAddress;

    public OrderDto(Integer id, LocalDateTime creatingDate, OrderStatus status, OrderTransporting transporting, LocalDateTime transportingDate, Integer cost, Integer apartmentNum, User user, Linoleum linoleum, Layout layout, DeliveryAddress deliveryAddress) {
        this.id = id;
        this.creatingDate = creatingDate;
        this.status = status;
        this.transporting = transporting;
        this.transportingDate = transportingDate;
        this.cost = cost;
        this.apartmentNum = apartmentNum;
        this.user = user;
        this.linoleum = linoleum;
        this.layout = layout;
        this.deliveryAddress = deliveryAddress;
    }

    public static class OrderDtoBuilder{
        private Integer id;
        private LocalDateTime creatingDate;
        private OrderStatus status;
        private OrderTransporting transporting;
        private LocalDateTime transportingDate;
        private Integer cost;
        private Integer apartmentNum;
        private User user;
        private Linoleum linoleum;
        private Layout layout;
        private DeliveryAddress deliveryAddress;

        public OrderDtoBuilder(){}

        public OrderDtoBuilder id(Integer id){
            this.id = id;
            return this;
        }

        public OrderDtoBuilder creatingDate(LocalDateTime creatingDate){
            this.creatingDate = creatingDate;
            return this;
        }

        public OrderDtoBuilder status(OrderStatus status){
            this.status = status;
            return this;
        }

        public OrderDtoBuilder transporting(OrderTransporting transporting){
            this.transporting = transporting;
            return this;
        }

        public OrderDtoBuilder transportingDate(LocalDateTime transportingDate){
            this.transportingDate = transportingDate;
            return this;
        }

        public OrderDtoBuilder cost(Integer cost){
            this.cost = cost;
            return this;
        }

        public OrderDtoBuilder apartmentNum(Integer apartmentNum){
            this.apartmentNum = apartmentNum;
            return this;
        }

        public OrderDtoBuilder user(User user){
            this.user = user;
            return this;
        }

        public OrderDtoBuilder linoleum(Linoleum linoleum){
            this.linoleum = linoleum;
            return this;
        }

        public OrderDtoBuilder layout(Layout layout){
            this.layout = layout;
            return this;
        }

        public OrderDtoBuilder deliveryAddress(DeliveryAddress deliveryAddress){
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderDto build(){
            return new OrderDto(this.id,this.creatingDate,this.status,this.transporting
            ,this.transportingDate,this.cost,this.apartmentNum,this.user
            ,this.linoleum,this.layout,this.deliveryAddress);
        }

        @Override
        public String toString() {
            return "OrderDtoBuilder{" +
                    "id=" + id +
                    ", creatingDate=" + creatingDate +
                    ", status=" + status +
                    ", transporting=" + transporting +
                    ", transportingDate=" + transportingDate +
                    ", cost=" + cost +
                    ", apartmentNum=" + apartmentNum +
                    ", user=" + user +
                    ", linoleum=" + linoleum +
                    ", layout=" + layout +
                    ", deliveryAddress=" + deliveryAddress +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) && Objects.equals(creatingDate, orderDto.creatingDate) && status == orderDto.status && transporting == orderDto.transporting && Objects.equals(transportingDate, orderDto.transportingDate) && Objects.equals(cost, orderDto.cost) && Objects.equals(apartmentNum, orderDto.apartmentNum) && Objects.equals(user, orderDto.user) && Objects.equals(linoleum, orderDto.linoleum) && Objects.equals(layout, orderDto.layout) && Objects.equals(deliveryAddress, orderDto.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creatingDate, status, transporting, transportingDate, cost, apartmentNum, user, linoleum, layout, deliveryAddress);
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getCreatingDate() {
        return creatingDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public OrderTransporting getTransporting() {
        return transporting;
    }

    public LocalDateTime getTransportingDate() {
        return transportingDate;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getApartmentNum() {
        return apartmentNum;
    }

    public User getUser() {
        return user;
    }

    public Linoleum getLinoleum() {
        return linoleum;
    }

    public Layout getLayout() {
        return layout;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public static OrderDtoBuilder builder(){
        return new OrderDtoBuilder();
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", creatingDate=" + creatingDate +
                ", status=" + status +
                ", transporting=" + transporting +
                ", transportingDate=" + transportingDate +
                ", cost=" + cost +
                ", apartmentNum=" + apartmentNum +
                ", user=" + user +
                ", linoleum=" + linoleum +
                ", layout=" + layout +
                ", deliveryAddress=" + deliveryAddress +
                '}';
    }
}
