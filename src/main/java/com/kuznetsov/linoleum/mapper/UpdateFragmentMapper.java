package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dto.UpdateFragmentDto;
import com.kuznetsov.linoleum.entity.Fragment;
import com.kuznetsov.linoleum.entity.FragmentType;

public class UpdateFragmentMapper implements Mapper<UpdateFragmentDto, Fragment> {
    private static final UpdateFragmentMapper INSTANCE = new UpdateFragmentMapper();
    private final LayoutsNamesDao layoutsNamesDao = LayoutsNamesDao.getInstance();

    private UpdateFragmentMapper(){}

    @Override
    public Fragment mapFrom(UpdateFragmentDto object) {
        return new Fragment(Integer.parseInt(object.getId())
                ,Float.parseFloat(object.getWidth())
                ,Float.parseFloat(object.getLength())
                , FragmentType.valueOf(object.getfType())
                ,layoutsNamesDao.findById(Integer.parseInt(object.getLayoutNameId())).get());
    }

    public static UpdateFragmentMapper getInstance(){
        return INSTANCE;
    }
}
