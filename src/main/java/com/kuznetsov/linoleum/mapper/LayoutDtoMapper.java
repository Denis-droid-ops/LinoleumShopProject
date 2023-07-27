package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.LayoutDto;
import com.kuznetsov.linoleum.entity.Layout;

public class LayoutDtoMapper implements Mapper<Layout, LayoutDto> {
    private static final LayoutDtoMapper INSTANCE = new LayoutDtoMapper();

    private LayoutDtoMapper(){}

    @Override
    public LayoutDto mapFrom(Layout object) {
        return new LayoutDto(object.getId()
        ,object.getCity()
        ,object.getStreet()
        ,object.getHomeNum()
        ,object.getRoomCount()
        ,object.getLayoutRowType()
        ,object.getlType()
        ,object.getLayoutName());
    }
    public static LayoutDtoMapper getInstance(){
        return INSTANCE;
    }
}
