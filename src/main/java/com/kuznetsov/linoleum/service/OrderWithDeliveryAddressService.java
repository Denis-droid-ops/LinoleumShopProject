package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.OrderWithDeliveryAddressDao;
import com.kuznetsov.linoleum.dto.CreateOrderDto;
import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithDeliveryAddress;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;
import com.kuznetsov.linoleum.mapper.OrderDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderWithDeliveryAddressService {
    private static final OrderWithDeliveryAddressService INSTANCE = new OrderWithDeliveryAddressService();
    private final OrderWithDeliveryAddressDao orderWithDeliveryAddressDao = OrderWithDeliveryAddressDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
    private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.getInstance();

    private OrderWithDeliveryAddressService(){}

    public OrderWithDeliveryAddress save(CreateOrderDto createOrderDto){
        OrderWithDeliveryAddress orderWithDeliveryAddress = (OrderWithDeliveryAddress) createOrderMapper.mapFrom(createOrderDto);
        return orderWithDeliveryAddressDao.save(orderWithDeliveryAddress);
    }

    public List<OrderDto> findAll(){
        return orderWithDeliveryAddressDao.findAll().stream().map(o->OrderDto.builder().id(o.getId())
                .creatingDate(o.getCreatingDate())
                .status(o.getStatus())
                .transporting(o.getTransporting())
                .transportingDate(o.getTransportingDate())
                .cost(o.getCost())
                .apartmentNum(o.getApartmentNum())
                .user(o.getUser())
                .linoleum(o.getLinoleum())
                .deliveryAddress(o.getDeliveryAddress()).build()).collect(Collectors.toList());
    }

    public Optional<OrderDto> findById(Integer id){
        return orderWithDeliveryAddressDao.findById(id).map(o->orderDtoMapper.mapFrom(o));
    }

    public static OrderWithDeliveryAddressService getInstance(){
        return INSTANCE;
    }
}
