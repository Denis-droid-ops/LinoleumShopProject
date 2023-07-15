package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.LayoutDao;
import com.kuznetsov.linoleum.dao.OrderDao;
import com.kuznetsov.linoleum.dto.CreateLayoutDto;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.dto.LayoutDto;
import com.kuznetsov.linoleum.entity.Layout;
import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.LayoutRowType;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.mapper.CreateLayoutMapper;
import com.kuznetsov.linoleum.mapper.LayoutDtoMapper;
import com.kuznetsov.linoleum.validator.CreateLayoutValidator;
import com.kuznetsov.linoleum.validator.ValidationResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LayoutService {

    private static final LayoutService INSTANCE = new LayoutService();
    private final LayoutDao layoutDao = LayoutDao.getInstance();
    private final CreateLayoutMapper createLayoutMapper = CreateLayoutMapper.getInstance();
    private final LayoutDtoMapper layoutDtoMapper = LayoutDtoMapper.getInstance();
    private final CreateLayoutValidator createLayoutValidator = CreateLayoutValidator.getInstance();

    private LayoutService(){}

    public Layout save(CreateLayoutDto createLayoutDto){
        ValidationResult validationResult = createLayoutValidator.isValid(createLayoutDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
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
