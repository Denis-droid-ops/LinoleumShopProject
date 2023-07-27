package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.RollDto;
import com.kuznetsov.linoleum.entity.Roll;

public class RollDtoMapper implements Mapper<Roll, RollDto>{
    private static final RollDtoMapper INSTANCE = new RollDtoMapper();

    private RollDtoMapper(){}

    @Override
    public RollDto mapFrom(Roll object) {
        return new RollDto(object.getId(),object.getPartNum()
                ,object.getWidth(),object.getLength(),object.isRemain(),object.getLinoleum());
    }
    public static RollDtoMapper getInstance(){
        return INSTANCE;
    }
}
