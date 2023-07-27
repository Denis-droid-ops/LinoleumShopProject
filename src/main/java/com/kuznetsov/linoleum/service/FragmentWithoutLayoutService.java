package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.FragmentDao;
import com.kuznetsov.linoleum.dao.FragmentWithoutLayoutDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentWithoutLayout;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.mapper.CreateFragmentMapper;
import com.kuznetsov.linoleum.mapper.CreateFragmentWithoutLayoutMapper;
import com.kuznetsov.linoleum.mapper.FragmentDtoMapper;
import com.kuznetsov.linoleum.mapper.FragmentWithoutLayoutDtoMapper;
import com.kuznetsov.linoleum.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FragmentWithoutLayoutService {
    private static final FragmentWithoutLayoutService INSTANCE = new FragmentWithoutLayoutService();
    private final FragmentWithoutLayoutDao fragmentWithoutLayoutDao = FragmentWithoutLayoutDao.getInstance();
    private final CreateFragmentWithoutLayoutMapper createFragmentWithoutLayoutMapper = CreateFragmentWithoutLayoutMapper.getInstance();
    private final FragmentWithoutLayoutDtoMapper fragmentWithoutLayoutDtoMapper = FragmentWithoutLayoutDtoMapper.getInstance();


    private FragmentWithoutLayoutService(){}

    public FragmentWithoutLayout save(CreateFragmentWithoutLayoutDto createFragmentWithoutLayoutDto){
        FragmentWithoutLayout fragmentWithoutLayout = createFragmentWithoutLayoutMapper.mapFrom(createFragmentWithoutLayoutDto);
        return fragmentWithoutLayoutDao.save(fragmentWithoutLayout);
    }

    public List<FragmentWithoutLayoutDto> findAllByOrderId(Integer orderId){
        return fragmentWithoutLayoutDao.findAllByOrderId(orderId).stream()
                .map(f->fragmentWithoutLayoutDtoMapper.mapFrom(f)).collect(Collectors.toList());
    }

    public Optional<FragmentDto> findById(Integer id){
       return Optional.empty();
    }

    public List<Fragment> findAll(){
        return null;
    }

    public static FragmentWithoutLayoutService getInstance(){
        return INSTANCE;
    }
}
