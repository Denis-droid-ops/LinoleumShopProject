package com.kuznetsov.linoleum.mapper;


import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithDeliveryAddress;
import com.kuznetsov.linoleum.entity.OrderWithLayout;

public class OrderDtoMapper implements Mapper<Order, OrderDto>{
    private static final OrderDtoMapper INSTANCE = new OrderDtoMapper();

    private OrderDtoMapper(){}

    @Override
    public OrderDto mapFrom(Order object) {
        if(object.getClass()==OrderWithLayout.class){
            return OrderDto.builder().id(object.getId())
                    .creatingDate(object.getCreatingDate())
                    .status(object.getStatus())
                    .transporting(object.getTransporting())
                    .transportingDate(object.getTransportingDate())
                    .cost(object.getCost())
                    .apartmentNum(object.getApartmentNum())
                    .user(object.getUser())
                    .linoleum(object.getLinoleum())
                    .layout(((OrderWithLayout) object).getLayout())
                    .build();
        }else {
            if (object.getClass() == OrderWithDeliveryAddress.class) {
                return OrderDto.builder().id(object.getId())
                        .creatingDate(object.getCreatingDate())
                        .status(object.getStatus())
                        .transporting(object.getTransporting())
                        .transportingDate(object.getTransportingDate())
                        .cost(object.getCost())
                        .apartmentNum(object.getApartmentNum())
                        .user(object.getUser())
                        .linoleum(object.getLinoleum())
                        .deliveryAddress(((OrderWithDeliveryAddress) object).getDeliveryAddress())
                        .build();
            }else {
                return OrderDto.builder().id(object.getId())
                        .creatingDate(object.getCreatingDate())
                        .status(object.getStatus())
                        .transporting(object.getTransporting())
                        .transportingDate(object.getTransportingDate())
                        .cost(object.getCost())
                        .apartmentNum(object.getApartmentNum())
                        .user(object.getUser())
                        .linoleum(object.getLinoleum())
                        .build();
            }
           }
    }
    public static OrderDtoMapper getInstance(){
        return INSTANCE;
    }
}
