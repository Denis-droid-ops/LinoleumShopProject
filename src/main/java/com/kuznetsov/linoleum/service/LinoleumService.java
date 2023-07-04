package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.entity.Linoleum;
import com.kuznetsov.linoleum.mapper.LinoleumDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LinoleumService {
    private static final LinoleumService INSTANCE = new LinoleumService();
    private LinoleumDtoMapper linoleumDtoMapper = LinoleumDtoMapper.getInstance();
    private LinoleumDao linoleumDao = LinoleumDao.getInstance();
    private LinoleumService(){}

    public List<LinoleumDto> findAll(){
        return linoleumDao.findAll().stream().map(linoleum -> new LinoleumDto(linoleum.getId()
        ,linoleum.getName(),linoleum.getProtect(),linoleum.getThickness(),linoleum.getPrice(),linoleum.getImagePath()))
                .collect(Collectors.toList());
    }

    public Optional<LinoleumDto> findById(Integer id){
        return Optional.ofNullable(linoleumDtoMapper.mapFrom(linoleumDao.findById(id).get()));
    }

    public static LinoleumService getInstance(){
        return INSTANCE;
    }
}
