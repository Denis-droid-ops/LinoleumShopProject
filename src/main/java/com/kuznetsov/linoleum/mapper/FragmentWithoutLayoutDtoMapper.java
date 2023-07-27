package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.FragmentWithoutLayoutDto;
import com.kuznetsov.linoleum.entity.FragmentWithoutLayout;

public class FragmentWithoutLayoutDtoMapper implements Mapper<FragmentWithoutLayout, FragmentWithoutLayoutDto> {
    private static final FragmentWithoutLayoutDtoMapper INSTANCE = new FragmentWithoutLayoutDtoMapper();

    private FragmentWithoutLayoutDtoMapper(){}

    @Override
    public FragmentWithoutLayoutDto mapFrom(FragmentWithoutLayout object) {
        return new FragmentWithoutLayoutDto(object.getId()
        ,object.getfWidth()
        ,object.getfLength()
        ,object.getOrder());
    }

    public static FragmentWithoutLayoutDtoMapper getInstance(){
        return INSTANCE;
    }
}
