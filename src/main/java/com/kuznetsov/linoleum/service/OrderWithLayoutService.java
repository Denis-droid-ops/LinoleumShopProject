package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.OrderWithLayoutDao;
import com.kuznetsov.linoleum.dto.CreateOrderDto;
import com.kuznetsov.linoleum.dto.OrderDto;
import com.kuznetsov.linoleum.entity.Order;
import com.kuznetsov.linoleum.entity.OrderWithLayout;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;

import java.util.List;

public class OrderWithLayoutService {
    private static final OrderWithLayoutService INSTANCE = new OrderWithLayoutService();
    private final OrderWithLayoutDao orderWithLayoutDao = OrderWithLayoutDao.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();

    private OrderWithLayoutService(){}

    public OrderWithLayout save(CreateOrderDto createOrderDto){
        OrderWithLayout orderWithLayout =(OrderWithLayout) createOrderMapper.mapFrom(createOrderDto);
        System.out.println(orderWithLayout.getId() + " " +orderWithLayout.getStatus()+"LAYOUT is "
        +orderWithLayout.getLayout()+" ");
        return orderWithLayoutDao.save(orderWithLayout);
    }

    public List<OrderDto> findAll(){
        return null;
    }

    public static OrderWithLayoutService getInstance(){
        return INSTANCE;
    }
}
