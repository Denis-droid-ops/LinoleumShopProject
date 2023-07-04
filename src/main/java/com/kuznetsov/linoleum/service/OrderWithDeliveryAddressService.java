package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.OrderWithDeliveryAddressDao;
import com.kuznetsov.linoleum.dto.CreateOrderDto;
import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithDeliveryAddress;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;

import java.util.List;

public class OrderWithDeliveryAddressService {
    private static final OrderWithDeliveryAddressService INSTANCE = new OrderWithDeliveryAddressService();
    private final OrderWithDeliveryAddressDao orderWithDeliveryAddressDao = OrderWithDeliveryAddressDao.getInstance();
    private CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();

    private OrderWithDeliveryAddressService(){}

    public OrderWithDeliveryAddress save(CreateOrderDto createOrderDto){
        OrderWithDeliveryAddress orderWithDeliveryAddress = (OrderWithDeliveryAddress) createOrderMapper.mapFrom(createOrderDto);
        return orderWithDeliveryAddressDao.save(orderWithDeliveryAddress);
    }

    public List<OrderDto> findAll(){
        return null;
    }

    public static OrderWithDeliveryAddressService getInstance(){
        return INSTANCE;
    }
}
