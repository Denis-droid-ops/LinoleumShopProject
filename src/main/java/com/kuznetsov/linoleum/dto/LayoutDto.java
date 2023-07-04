package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.LayoutName;
import com.kuznetsov.linoleum.entity.LayoutRowType;
import com.kuznetsov.linoleum.entity.LayoutType;

import java.util.Objects;

public final class LayoutDto {
    private final Integer id;
    private final String city;
    private final String street;
    private final String homeNum;
    private final Integer roomCount;
    private final LayoutRowType layoutRowType;
    private final LayoutType lType;
    private final LayoutName layoutName;

    public LayoutDto(Integer id, String city, String street, String homeNum, Integer roomCount, LayoutRowType layoutRowType, LayoutType lType, LayoutName layoutName) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.roomCount = roomCount;
        this.layoutRowType = layoutRowType;
        this.lType = lType;
        this.layoutName = layoutName;
    }

    public Integer getId() {
        return id;
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

    public Integer getRoomCount() {
        return roomCount;
    }

    public LayoutRowType getLayoutRowType() {
        return layoutRowType;
    }

    public LayoutType getlType() {
        return lType;
    }

    public LayoutName getLayoutName() {
        return layoutName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LayoutDto)) return false;
        LayoutDto layoutDto = (LayoutDto) o;
        return Objects.equals(id, layoutDto.id) && Objects.equals(city, layoutDto.city) && Objects.equals(street, layoutDto.street) && Objects.equals(homeNum, layoutDto.homeNum) && Objects.equals(roomCount, layoutDto.roomCount) && layoutRowType == layoutDto.layoutRowType && lType == layoutDto.lType && Objects.equals(layoutName, layoutDto.layoutName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, homeNum, roomCount, layoutRowType, lType, layoutName);
    }

    @Override
    public String toString() {
        return "LayoutDto{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNum=" + homeNum +
                ", roomCount=" + roomCount +
                ", layoutRowType=" + layoutRowType +
                ", lType=" + lType +
                ", layoutName=" + layoutName +
                '}';
    }
}
