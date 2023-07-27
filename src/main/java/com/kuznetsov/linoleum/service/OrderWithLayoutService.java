package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.OrderWithLayoutDao;
import com.kuznetsov.linoleum.dto.CreateOrderDto;
import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithLayout;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;
import com.kuznetsov.linoleum.mapper.OrderDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderWithLayoutService {
    private static final OrderWithLayoutService INSTANCE = new OrderWithLayoutService();
    private final OrderWithLayoutDao orderWithLayoutDao = OrderWithLayoutDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();
    private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.getInstance();

    private OrderWithLayoutService(){}

    public OrderWithLayout save(CreateOrderDto createOrderDto){
        OrderWithLayout orderWithLayout =(OrderWithLayout) createOrderMapper.mapFrom(createOrderDto);
        System.out.println(orderWithLayout.getId() + " " +orderWithLayout.getStatus()+"LAYOUT is "
        +orderWithLayout.getLayout()+" ");
        return orderWithLayoutDao.save(orderWithLayout);
    }

    public List<OrderDto> findAll(){
        return orderWithLayoutDao.findAll().stream().map(o->OrderDto.builder().id(o.getId())
                .creatingDate(o.getCreatingDate())
                .status(o.getStatus())
                .transporting(o.getTransporting())
                .transportingDate(o.getTransportingDate())
                .cost(o.getCost())
                .apartmentNum(o.getApartmentNum())
                .user(o.getUser())
                .linoleum(o.getLinoleum())
                .layout(o.getLayout()).build()).collect(Collectors.toList());
    }

    public Optional<OrderDto> findById(Integer id){
        return orderWithLayoutDao.findById(id).map(o->orderDtoMapper.mapFrom(o));
    }

    public static OrderWithLayoutService getInstance(){
        return INSTANCE;
    }
}
