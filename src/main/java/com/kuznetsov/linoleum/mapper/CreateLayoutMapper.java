package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dao.LayoutDao;
import com.kuznetsov.linoleum.dao.LayoutsNamesDao;
import com.kuznetsov.linoleum.dto.*;
import com.kuznetsov.linoleum.entity.*;
import com.kuznetsov.linoleum.service.LayoutNameService;

import java.time.LocalDateTime;

public class CreateLayoutMapper implements Mapper<CreateLayoutDto,Layout>{
    private static final CreateLayoutMapper INSTANCE = new CreateLayoutMapper();
    private final LayoutNameService layoutNameService =  LayoutNameService.getInstance();
    private final LayoutsNamesDao layoutsNamesDao =  LayoutsNamesDao.getInstance();

    private CreateLayoutMapper(){}
    public Layout mapFrom(CreateLayoutDto createLayoutDto) {
        Layout layout = onServerSideSettedLayout();
        layout.setCity(createLayoutDto.getCity());
        layout.setStreet(createLayoutDto.getStreet());
        layout.setHomeNum(createLayoutDto.getHomeNum());
        layout.setRoomCount(Integer.valueOf(createLayoutDto.getRoomCount()));
        layout.setLayoutRowType(LayoutRowType.valueOf(createLayoutDto.getLayoutRowType()));

        LayoutName newLayoutName = layoutNameService.save(createLayoutDto.getCreateLayoutNameDto());
        layout.setLayoutName(newLayoutName);

        return layout;
    }

    private Layout onServerSideSettedLayout(){
        Layout part = new Layout();
        part.setlType(LayoutType.valueOf("CUSTOM"));
        return part;
    }

    public static CreateLayoutMapper getInstance(){
        return INSTANCE;
    }
}
