package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.FragmentDao;
import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Apartment;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentType;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.mapper.CreateFragmentMapper;
import com.kuznetsov.linoleum.mapper.CreateLayoutNameMapper;
import com.kuznetsov.linoleum.mapper.FragmentDtoMapper;
import com.kuznetsov.linoleum.mapper.UpdateFragmentMapper;
import com.kuznetsov.linoleum.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FragmentService {
    private static final FragmentService INSTANCE = new FragmentService();
    private final FragmentDao fragmentDao = FragmentDao.getInstance();
    private final CreateFragmentMapper createFragmentMapper = CreateFragmentMapper.getInstance();
    private final FragmentDtoMapper fragmentDtoMapper = FragmentDtoMapper.getInstance();
    private final UpdateFragmentMapper updateFragmentMapper = UpdateFragmentMapper.getInstance();

    private FragmentService(){}

    public Fragment save(CreateFragmentDto createFragmentDto){
        Fragment fragment = createFragmentMapper.mapFrom(createFragmentDto);
        return fragmentDao.save(fragment);
    }

    public void saveWithOrder(Integer fragmentId,Integer orderId) {
        fragmentDao.saveWithOrder(fragmentId,orderId);
    }

    public List<FragmentDto> findAllByLayoutNameId(Integer layoutNameId){
        return fragmentDao.findAllByLayoutNameId(layoutNameId).stream()
                .map(f->new FragmentDto(f.getId(),f.getWidth()
                        ,f.getLength(),f.getfType(),f.getLayoutName()))
                .collect(Collectors.toList());
    }
    public Optional<FragmentDto> findById(Integer id){
        return Optional.ofNullable(fragmentDtoMapper.mapFrom(fragmentDao.findById(id).get()));
    }

    public List<Fragment> findAll(){
        return fragmentDao.findAll();
    }

    public List<FragmentDto> findAllWithOrders(){
        return fragmentDao.findAllWithOrders().stream().map(f->new FragmentDto(f.getId()
        ,f.getWidth(),f.getLength(),f.getfType(),f.getLayoutName())).collect(Collectors.toList());
    }

    public List<FragmentDto> findAllWithOrdersByOrderId(Integer orderId) {
        return fragmentDao.findAllWithOrdersByOrderId(orderId).stream().map(f->new FragmentDto(f.getId()
        ,f.getWidth(),f.getLength(),f.getfType(),f.getLayoutName())).collect(Collectors.toList());
    }

    public void update(UpdateFragmentDto updateFragmentDto) {
        fragmentDao.update(updateFragmentMapper.mapFrom(updateFragmentDto));
    }

    public boolean delete(Integer id) {
        return fragmentDao.delete(id);
    }

    public static FragmentService getInstance(){
        return INSTANCE;
    }

}
