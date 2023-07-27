package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dao.LinoleumDao;
import com.kuznetsov.linoleum.dto.CreateRollDto;
import com.kuznetsov.linoleum.entity.Roll;

public class CreateRollMapper implements Mapper<CreateRollDto, Roll>{
    private static final CreateRollMapper INSTANCE = new CreateRollMapper();
    private final LinoleumDao linoleumDao = LinoleumDao.getInstance();

    private CreateRollMapper(){}

    @Override
    public Roll mapFrom(CreateRollDto object) {
        Roll roll = new Roll();
        roll.setPartNum(Integer.parseInt(object.getPartNum()));
        roll.setWidth(Float.parseFloat(object.getrWidth()));
        roll.setLength(Float.parseFloat(object.getrLength()));
        roll.setRemain(false); //setted on server
        roll.setLinoleum(linoleumDao.findById(Integer.parseInt(object.getLinoleumId())).get());
        return roll;
    }

    public static CreateRollMapper getInstance(){
        return INSTANCE;
    }

}
