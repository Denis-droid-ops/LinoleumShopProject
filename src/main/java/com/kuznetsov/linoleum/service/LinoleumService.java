package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.LinoleumDto;

import java.util.List;
import java.util.stream.Collectors;

public class LinoleumService {
    private static final LinoleumService INSTANCE = new LinoleumService();
    private final LinoleumDao linoleumDao = LinoleumDao.getInstance();
    private LinoleumService(){}

    public List<LinoleumDto> findAll(){
        return linoleumDao.findAll().stream().map(linoleum -> new LinoleumDto(linoleum.getId()
        ,linoleum.getName(),linoleum.getProtect(),linoleum.getThickness(),linoleum.getPrice(),linoleum.getImagePath()))
                .collect(Collectors.toList());
    }

    public static LinoleumService getInstance(){
        return INSTANCE;
    }
}
