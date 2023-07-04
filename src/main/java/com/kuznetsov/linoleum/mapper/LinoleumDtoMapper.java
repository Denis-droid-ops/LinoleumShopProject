package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.LinoleumDto;
import com.kuznetsov.linoleum.entity.Linoleum;

public class LinoleumDtoMapper implements Mapper<Linoleum, LinoleumDto>{
    private static final LinoleumDtoMapper INSTANCE = new LinoleumDtoMapper();
    private LinoleumDtoMapper(){}
    @Override
    public LinoleumDto mapFrom(Linoleum object) {
        return new LinoleumDto(object.getId()
        ,object.getName()
        ,object.getProtect()
        ,object.getThickness()
        ,object.getPrice()
        ,object.getImagePath());
    }

    public static LinoleumDtoMapper getInstance(){
        return INSTANCE;
    }
}
