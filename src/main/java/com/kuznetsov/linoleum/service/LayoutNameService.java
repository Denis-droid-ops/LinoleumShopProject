package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.exception.DAOException;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.mapper.CreateLayoutNameMapper;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.validator.ValidationResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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

    public boolean delete(Integer id) {
        return layoutsNamesDao.delete(id);
    }

    //do not using dto, because LayoutName has a one variable
    //if any variables will be added, reccomended to use updateDto and Mapper
    public void update(LayoutName entity) {
        layoutsNamesDao.update(entity);
    }

    public Optional<LayoutName> findById(Integer layoutNameId){
        return layoutsNamesDao.findById(layoutNameId);
    }

    public static LayoutNameService getInstance(){
        return INSTANCE;
    }
}
