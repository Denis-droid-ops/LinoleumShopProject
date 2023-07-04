package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.CreateApartmentDto;
import com.kuznetsov.linoleum.dto.CreateLayoutDto;
import com.kuznetsov.linoleum.dto.CreateLayoutNameDto;
import com.kuznetsov.linoleum.entity.Apartment;
import com.kuznetsov.linoleum.entity.Layout;
import com.kuznetsov.linoleum.entity.LayoutRowType;
import com.kuznetsov.linoleum.entity.LayoutType;
import com.kuznetsov.linoleum.service.LayoutNameService;
import com.kuznetsov.linoleum.service.LayoutService;

public class CreateApartmentMapper {
    private static final CreateApartmentMapper INSTANCE = new CreateApartmentMapper();
    private LayoutService layoutService =  LayoutService.getInstance();

    private CreateApartmentMapper(){}

    public Apartment mapFrom(CreateApartmentDto createApartmentDto, CreateLayoutDto createLayoutDto,CreateLayoutNameDto createLayoutNameDto) {
        Apartment apartment = new Apartment();
        apartment.setNumber(Integer.valueOf(createApartmentDto.getNumber()));
        apartment.setLayout(layoutService.save(createLayoutDto));
        return apartment;
    }

    public static CreateApartmentMapper getInstance(){
        return INSTANCE;
    }

}
