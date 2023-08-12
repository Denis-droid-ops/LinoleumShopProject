package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateLayoutDto {
    private final String city;
    private final String street;
    private final String homeNum;
    private final String roomCount;
    private final String layoutRowType;
    //Using not final variables because they must not be initialized together
    private  String layoutNameId;
    //if new layout creating, then creating new CUSTOM layoutName, not choosing from existed
    //only admin can update and choose layoutName
    private CreateLayoutNameDto createLayoutNameDto;

    public CreateLayoutDto(String city, String street, String homeNum, String roomCount, String layoutRowType, CreateLayoutNameDto createLayoutNameDto) {
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.roomCount = roomCount;
        this.layoutRowType = layoutRowType;
        this.createLayoutNameDto = createLayoutNameDto;
    }

    public CreateLayoutDto(String city, String street, String homeNum, String roomCount, String layoutRowType, String layoutNameId) {
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.roomCount = roomCount;
        this.layoutRowType = layoutRowType;
        this.layoutNameId = layoutNameId;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHomeNum() {
        return homeNum;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public String getLayoutRowType() {
        return layoutRowType;
    }

    public CreateLayoutNameDto getCreateLayoutNameDto() {
        return createLayoutNameDto;
    }

    public String getLayoutNameId() {
        return layoutNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateLayoutDto)) return false;
        CreateLayoutDto that = (CreateLayoutDto) o;
        return Objects.equals(city, that.city) && Objects.equals(street, that.street) && Objects.equals(homeNum, that.homeNum) && Objects.equals(roomCount, that.roomCount) && Objects.equals(layoutRowType, that.layoutRowType) && Objects.equals(createLayoutNameDto, that.createLayoutNameDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, homeNum, roomCount, layoutRowType, createLayoutNameDto);
    }
}
