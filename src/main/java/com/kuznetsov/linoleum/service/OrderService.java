package com.kuznetsov.linoleum.service;


import com.kuznetsov.linoleum.dao.*;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.mapper.CreateOrderMapper;
import com.kuznetsov.linoleum.mapper.OrderDtoMapper;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.validator.CreateLayoutValidator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderService {
    private static final OrderService INSTANCE = new OrderService();
    private final OrderDao orderDao = OrderDao.getInstance();
    private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.getInstance();
    private final CreateOrderMapper createOrderMapper = CreateOrderMapper.getInstance();


    private final List<CreateFragmentWithoutLayoutDto> withoutLayoutFragments  =  new ArrayList<>();
    private final List<CreateFragmentDto> customLayoutFragments = new ArrayList<>();


    private OrderService(){}

    public List<OrderDto> findAll(){
        return orderDao.findAll().stream().map(o->{
                OrderDto orderDto = OrderDto.builder().id(o.getId())
                        .creatingDate(o.getCreatingDate())
                        .status(o.getStatus())
                        .transporting(o.getTransporting())
                        .transportingDate(o.getTransportingDate())
                        .cost(o.getCost())
                        .apartmentNum(o.getApartmentNum())
                        .user(o.getUser())
                        .linoleum(o.getLinoleum())
                        .build();

                if(o.getClass()==OrderWithLayout.class)
                return OrderDto.builder().id(o.getId())
                .creatingDate(o.getCreatingDate())
                .status(o.getStatus())
                .transporting(o.getTransporting())
                .transportingDate(o.getTransportingDate())
                .cost(o.getCost())
                .apartmentNum(o.getApartmentNum())
                .user(o.getUser())
                .linoleum(o.getLinoleum())
                .layout(((OrderWithLayout)o).getLayout())
                .build();

            if(o.getClass()==OrderWithDeliveryAddress.class)
                return OrderDto.builder().id(o.getId())
                        .creatingDate(o.getCreatingDate())
                        .status(o.getStatus())
                        .transporting(o.getTransporting())
                        .transportingDate(o.getTransportingDate())
                        .cost(o.getCost())
                        .apartmentNum(o.getApartmentNum())
                        .user(o.getUser())
                        .linoleum(o.getLinoleum())
                        .deliveryAddress(((OrderWithDeliveryAddress)o).getDeliveryAddress())
                        .build();

             return orderDto;
             }).collect(Collectors.toList());
    }

    public List<OrderDto> findAllByUserId(Integer userId) {
        return orderDao.findAllByUserId(userId).stream().map(o->{
            OrderDto orderDto = OrderDto.builder().id(o.getId())
                    .creatingDate(o.getCreatingDate())
                    .status(o.getStatus())
                    .transporting(o.getTransporting())
                    .transportingDate(o.getTransportingDate())
                    .cost(o.getCost())
                    .apartmentNum(o.getApartmentNum())
                    .user(o.getUser())
                    .linoleum(o.getLinoleum())
                    .build();

            if(o.getClass()==OrderWithLayout.class)
                return OrderDto.builder().id(o.getId())
                        .creatingDate(o.getCreatingDate())
                        .status(o.getStatus())
                        .transporting(o.getTransporting())
                        .transportingDate(o.getTransportingDate())
                        .cost(o.getCost())
                        .apartmentNum(o.getApartmentNum())
                        .user(o.getUser())
                        .linoleum(o.getLinoleum())
                        .layout(((OrderWithLayout)o).getLayout())
                        .build();

            if(o.getClass()==OrderWithDeliveryAddress.class)
                return OrderDto.builder().id(o.getId())
                        .creatingDate(o.getCreatingDate())
                        .status(o.getStatus())
                        .transporting(o.getTransporting())
                        .transportingDate(o.getTransportingDate())
                        .cost(o.getCost())
                        .apartmentNum(o.getApartmentNum())
                        .user(o.getUser())
                        .linoleum(o.getLinoleum())
                        .deliveryAddress(((OrderWithDeliveryAddress)o).getDeliveryAddress())
                        .build();

            return orderDto;
        }).collect(Collectors.toList());
    }

    public Optional<OrderDto> findById(Integer id){
        return orderDao.findById(id).map(o->orderDtoMapper.mapFrom(o));
    }

    public Order save(CreateOrderDto createOrderDto){
        Order order = createOrderMapper.mapFrom(createOrderDto);
        return orderDao.save(order);
    }

    public void updateStatus(Integer orderId,OrderStatus orderStatus){
        orderDao.updateStatus(orderId,orderStatus);
    }

    public boolean delete(Integer orderId){
        return orderDao.delete(orderId);
    }

    //calculate order cost (also for custom layout and without layout fragments)
    public int calcCost(LinoleumDto linoleumDto, List<FragmentDto> orderFragments){
        double area = orderFragments.stream().mapToDouble(f->f.getWidth()*f.getLength()).sum();
        return (int) (area*linoleumDto.getPrice());
    }
    public int calcCostForWithoutLayoutFragments(LinoleumDto linoleumDto){
        double area = withoutLayoutFragments.stream().mapToDouble(f->Float.parseFloat(f.getfWidth())*Float.parseFloat(f.getfLength())).sum();
        return (int) (area*linoleumDto.getPrice());
    }

    public int calcCostForCustomLayoutFragments(LinoleumDto linoleumDto){
        double area = customLayoutFragments.stream().mapToDouble(f->Float.parseFloat(f.getWidth())*Float.parseFloat(f.getLength())).sum();
        return (int) (area*linoleumDto.getPrice());
    }

    public List<CreateFragmentWithoutLayoutDto> getWithoutLayoutFragments(){
        return withoutLayoutFragments;
    }

    public List<CreateFragmentDto> getCustomLayoutFragments(){
        return customLayoutFragments;
    }

    public void clearWithoutLayoutFragments(){
        withoutLayoutFragments.clear();
    }

    public void clearCustomLayoutFragments(){
        customLayoutFragments.clear();
    }

    public static OrderService getInstance(){
        return INSTANCE;
    }
}
