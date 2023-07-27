package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.FragmentDto;
import com.kuznetsov.linoleum.entity.Fragment;

public class FragmentDtoMapper implements Mapper<Fragment, FragmentDto> {
    private static final FragmentDtoMapper INSTANCE = new FragmentDtoMapper();

    private FragmentDtoMapper(){}

    @Override
    public FragmentDto mapFrom(Fragment object) {
        return new FragmentDto(object.getId()
        , object.getWidth()
        ,object.getLength()
        , object.getfType()
        ,object.getLayoutName());
    }
    public static FragmentDtoMapper getInstance(){
        return INSTANCE;
    }
}
