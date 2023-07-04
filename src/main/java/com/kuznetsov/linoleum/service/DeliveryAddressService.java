package com.kuznetsov.linoleum.service;


import com.kuznetsov.linoleum.dao.DeliveryAddressDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Apartment;
import com.kuznetsov.linoleum.entity.DeliveryAddress;
import com.kuznetsov.linoleum.mapper.CreateDeliveryAddressesMapper;
import com.kuznetsov.linoleum.mapper.DeliveryAddressDtoMapper;

import java.util.List;
import java.util.Optional;

public class DeliveryAddressService {
    private static final DeliveryAddressService INSTANCE = new DeliveryAddressService();
    private DeliveryAddressDao deliveryAddressDao = DeliveryAddressDao.getInstance();
    private DeliveryAddressDtoMapper deliveryAddressDtoMapper = DeliveryAddressDtoMapper.getInstance();
    private CreateDeliveryAddressesMapper createDeliveryAddressesMapper = CreateDeliveryAddressesMapper.getInstance();

    public DeliveryAddress save(CreateDeliveryAddressDto createDeliveryAddressDto){
        DeliveryAddress deliveryAddress = createDeliveryAddressesMapper.mapFrom(createDeliveryAddressDto);
        return deliveryAddressDao.save(deliveryAddress);
    }

    public Optional<DeliveryAddressDto> findById(Integer id){
        return deliveryAddressDao.findById(id).map(da->deliveryAddressDtoMapper.mapFrom(da));
    }

    public static DeliveryAddressService getInstance(){
        return INSTANCE;
    }
}
