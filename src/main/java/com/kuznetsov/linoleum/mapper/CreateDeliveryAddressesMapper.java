package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.CreateDeliveryAddressDto;
import com.kuznetsov.linoleum.entity.DeliveryAddress;

public class CreateDeliveryAddressesMapper implements Mapper<CreateDeliveryAddressDto, DeliveryAddress>{
    private static final CreateDeliveryAddressesMapper INSTANCE = new CreateDeliveryAddressesMapper();

    @Override
    public DeliveryAddress mapFrom(CreateDeliveryAddressDto object) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setdCity(object.getdCity());
        deliveryAddress.setdStreet(object.getdStreet());
        deliveryAddress.setdHomeNum(object.getdHomeNum());
        return deliveryAddress;
    }

    public static CreateDeliveryAddressesMapper getInstance(){
        return INSTANCE;
    }
}
