package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LayoutDao;
import com.kuznetsov.linoleum.dao.OrderDao;
import com.kuznetsov.linoleum.dto.CreateLayoutDto;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.LayoutDto;
import com.kuznetsov.linoleum.entity.Layout;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.LayoutRowType;
import com.kuznetsov.linoleum.mapper.CreateLayoutMapper;
import com.kuznetsov.linoleum.mapper.LayoutDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LayoutService {

    private static final LayoutService INSTANCE = new LayoutService();
    private LayoutDao layoutDao = LayoutDao.getInstance();
    private CreateLayoutMapper createLayoutMapper = CreateLayoutMapper.getInstance();
    private LayoutDtoMapper layoutDtoMapper = LayoutDtoMapper.getInstance();

    private LayoutService(){}

    public Layout save(CreateLayoutDto createLayoutDto){
        Layout layout = createLayoutMapper.mapFrom(createLayoutDto);
        return layoutDao.save(layout);
    }

    public List<LayoutDto> findAll(){
        return layoutDao.findAll().stream().map(l->new LayoutDto(l.getId(),l.getCity()
        ,l.getStreet(),l.getHomeNum(),l.getRoomCount(),l.getLayoutRowType(),l.getlType(),l.getLayoutName()))
                .collect(Collectors.toList());
    }

    public Optional<LayoutDto> findByManyFields(String city,String street,String homeNum,Integer roomCount,LayoutRowType layoutRowType){
        Optional<Layout> layout = layoutDao.findByManyFields(city,street,homeNum,roomCount,layoutRowType);
        return layout.map(l->layoutDtoMapper.mapFrom(l));
    }

    public List<Layout> findAllTemplate(){
        return layoutDao.findAllTemplate();
    }

    public static LayoutService getInstance(){
        return INSTANCE;
    }
}
