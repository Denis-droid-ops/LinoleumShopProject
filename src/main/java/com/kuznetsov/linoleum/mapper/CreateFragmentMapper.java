package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dto.CreateFragmentDto;

import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentType;


public class CreateFragmentMapper {
    private static final CreateFragmentMapper INSTANCE = new CreateFragmentMapper();
    private final LayoutsNamesDao layoutsNamesDao = LayoutsNamesDao.getInstance();

    private CreateFragmentMapper(){}

    public Fragment mapFrom(CreateFragmentDto createFragmentDto) {
        Fragment fragment = new Fragment();
        fragment.setWidth(Float.valueOf(createFragmentDto.getWidth()));
        fragment.setLength(Float.valueOf(createFragmentDto.getLength()));
        fragment.setfType(FragmentType.valueOf(createFragmentDto.getfType()));
        fragment.setLayoutName(layoutsNamesDao.findById(Integer.valueOf(createFragmentDto.getLayoutNameId())).get());
        return fragment;
    }

    public static CreateFragmentMapper getInstance(){
        return INSTANCE;
    }

}
