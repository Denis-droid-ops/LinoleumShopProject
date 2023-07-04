package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.DeliveryAddressDto;
import com.kuznetsov.linoleum.entity.DeliveryAddress;

public class DeliveryAddressDtoMapper implements Mapper<DeliveryAddress, DeliveryAddressDto>{
    private static final DeliveryAddressDtoMapper INSTANCE = new DeliveryAddressDtoMapper();
    @Override
    public DeliveryAddressDto mapFrom(DeliveryAddress object) {
        return new DeliveryAddressDto(object.getId()
        ,object.getdCity()
        ,object.getdStreet()
        ,object.getdHomeNum());
    }

    public static DeliveryAddressDtoMapper getInstance(){
        return INSTANCE;
    }
}
