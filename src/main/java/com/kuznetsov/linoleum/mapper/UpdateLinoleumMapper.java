package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.UpdateLinoleumDto;
import com.kuznetsov.linoleum.entity.Linoleum;

public class UpdateLinoleumMapper implements Mapper<UpdateLinoleumDto, Linoleum>{
    private static final UpdateLinoleumMapper INSTANCE = new UpdateLinoleumMapper();
    private static final String IMAGE_FOLDER = "/resources/images/";

    private UpdateLinoleumMapper(){}

    @Override
    public Linoleum mapFrom(UpdateLinoleumDto object) {
        Linoleum linoleum  = new Linoleum();
        if(object.getDefaultImagePath()==null) {
            linoleum.setImagePath(IMAGE_FOLDER + object.getImagePath().getSubmittedFileName());
        }else {
            linoleum.setImagePath(object.getDefaultImagePath());
        }
        linoleum.setId(Integer.parseInt(object.getId()));
        linoleum.setName(object.getName());
        linoleum.setProtect(Float.parseFloat(object.getProtect()));
        linoleum.setThickness(Float.parseFloat(object.getThickness()));
        linoleum.setPrice(Integer.parseInt(object.getPrice()));
        return linoleum;
    }

    public static UpdateLinoleumMapper getInstance(){
        return INSTANCE;
    }
}
