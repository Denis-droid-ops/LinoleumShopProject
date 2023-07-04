package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.entity.LayoutName;

public class CreateLayoutNameMapper implements Mapper<CreateLayoutNameDto,LayoutName> {
    private static final CreateLayoutNameMapper INSTANCE = new CreateLayoutNameMapper();

    private CreateLayoutNameMapper(){}

    @Override
    public LayoutName mapFrom(CreateLayoutNameDto object) {
        LayoutName layoutName = new LayoutName();
        layoutName.setLnName(object.getLnName());
        return layoutName;
    }

    public static CreateLayoutNameMapper getInstance(){
        return INSTANCE;
    }
}
