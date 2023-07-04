package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.mapper.CreateLayoutNameMapper;
import com.kuznetsov.linoleum.validator.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public class LayoutNameService {
    private static final LayoutNameService INSTANCE = new LayoutNameService();
    private LayoutsNamesDao layoutsNamesDao = LayoutsNamesDao.getInstance();
    private CreateLayoutNameMapper createLayoutNameMapper = CreateLayoutNameMapper.getInstance();

    private LayoutNameService() {
    }

    public LayoutName save(CreateLayoutNameDto createLayoutNameDto){
        LayoutName layoutName = createLayoutNameMapper.mapFrom(createLayoutNameDto);
        return layoutsNamesDao.save(layoutName);
    }

    public List<LayoutName> findAll(){
        return layoutsNamesDao.findAll();
    }

    public static LayoutNameService getInstance(){
        return INSTANCE;
    }
}
