package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.CreateLinoleumDto;
import com.kuznetsov.linoleum.entity.Linoleum;

public class CreateLinoleumMapper implements Mapper<CreateLinoleumDto, Linoleum>{
    private static final CreateLinoleumMapper INSTANCE = new CreateLinoleumMapper();
    private static final String IMAGE_FOLDER = "/resources/images/";

    private CreateLinoleumMapper(){}

    @Override
    public Linoleum mapFrom(CreateLinoleumDto object) {
        Linoleum linoleum =  new Linoleum();
        linoleum.setName(object.getName());
        linoleum.setProtect(Float.parseFloat(object.getProtect()));
        linoleum.setThickness(Float.parseFloat(object.getThickness()));
        linoleum.setPrice(Integer.parseInt(object.getPrice()));
        linoleum.setImagePath(IMAGE_FOLDER+object.getImagePath().getSubmittedFileName());
        return linoleum;
    }

    public static CreateLinoleumMapper getInstance(){
        return INSTANCE;
    }
}
