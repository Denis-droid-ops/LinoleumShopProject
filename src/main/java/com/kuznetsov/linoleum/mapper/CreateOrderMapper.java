package com.kuznetsov.linoleum.mapper;


import com.kuznetsov.linoleum.dao.LayoutDao;
import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.service.DeliveryAddressService;
import com.kuznetsov.linoleum.service.LayoutNameService;
import com.kuznetsov.linoleum.service.LayoutService;
import com.kuznetsov.linoleum.util.LocalDateTimeFormatter;

import java.time.LocalDateTime;


public class CreateOrderMapper implements Mapper<CreateOrderDto,Order>{
    private static final CreateOrderMapper INSTANCE = new CreateOrderMapper();
    private final UserDao userDao = UserDao.getInstance();
    private final LinoleumDao linoleumDao = LinoleumDao.getInstance();
    private final LayoutDao layoutDao = LayoutDao.getInstance();
    private final LayoutService layoutService = LayoutService.getInstance();
    private final DeliveryAddressService deliveryAddressService = DeliveryAddressService.getInstance();

    //private int layoutNameCounter = 0;

    private CreateOrderMapper(){}

    //setting data only from createOrderDto
    public Order mapFrom(CreateOrderDto createOrderDto) {
        //setting order without any layout, without delivery address
        Order order = onServerSideSettedOrder();
        order.setTransporting(OrderTransporting.valueOf(createOrderDto.getTransporting()));
        order.setTransportingDate(LocalDateTimeFormatter.format(createOrderDto.getTransportingDate()));
        order.setCost(Integer.valueOf(createOrderDto.getCost()));
        order.setApartmentNum(Integer.valueOf(createOrderDto.getApartmentNum()));
        order.setUser(userDao.findById(Integer.valueOf(createOrderDto.getUserId())).get());
        order.setLinoleum(linoleumDao.findById(Integer.valueOf(createOrderDto.getLinoleumId())).get());

        //setting order with existed layout
        if(createOrderDto.getLayoutId()!=null){
            OrderWithLayout orderWithLayout = new OrderWithLayout(null,order.getCreatingDate()
            ,order.getStatus(),order.getTransporting(),order.getTransportingDate(),order.getCost()
            ,order.getApartmentNum(),order.getUser(),order.getLinoleum(),null);
            orderWithLayout.setLayout(layoutDao.findById(Integer.valueOf(createOrderDto.getLayoutId())).get());
            return orderWithLayout;
        }else {
            //setting order with new layout
            if(createOrderDto.getCreateLayoutDto()!=null){
                OrderWithLayout orderWithNewLayout = new OrderWithLayout(null,order.getCreatingDate()
                        ,order.getStatus(),order.getTransporting(),order.getTransportingDate(),order.getCost()
                        ,order.getApartmentNum(),order.getUser(),order.getLinoleum(),null);
                orderWithNewLayout.setLayout(layoutService.save(createOrderDto.getCreateLayoutDto()));
                return orderWithNewLayout;
            }else {
                //setting order without any layout, but delivery address exist
                if(createOrderDto.getCreateDeliveryAddressDto()!=null){
                   OrderWithDeliveryAddress orderWithDeliveryAddress = new OrderWithDeliveryAddress(null
                   ,order.getCreatingDate(),order.getStatus(),order.getTransporting(),order.getTransportingDate()
                   ,order.getCost(),order.getApartmentNum(),order.getUser(),order.getLinoleum(),null);
                   orderWithDeliveryAddress
                           .setDeliveryAddress(deliveryAddressService.save(createOrderDto.getCreateDeliveryAddressDto()));
                   return orderWithDeliveryAddress;
                }
            }
        }

        return order;
    }



    //setting part of order on server side
    private Order onServerSideSettedOrder(){
        Order part = new Order();
        part.setCreatingDate(LocalDateTime.now());
        part.setStatus(OrderStatus.NOTCOMPLETED);
        return part;
    }

    public static CreateOrderMapper getInstance(){
        return INSTANCE;
    }
}
