package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class Layout {
    private Integer id;
    private String city;
    private String street;
    private String homeNum;
    private Integer roomCount;
    private LayoutRowType layoutRowType;
    private LayoutType lType;
    private LayoutName layoutName;

    public Layout() {
    }

    public Layout(Integer id, String city, String street, String homeNum, Integer roomCount, LayoutRowType layoutRowType, LayoutType lType, LayoutName layoutName) {
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(String homeNum) {
        this.homeNum = homeNum;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public LayoutRowType getLayoutRowType() {
        return layoutRowType;
    }

    public void setLayoutRowType(LayoutRowType layoutRowType) {
        this.layoutRowType = layoutRowType;
    }

    public LayoutType getlType() {
        return lType;
    }

    public void setlType(LayoutType lType) {
        this.lType = lType;
    }

    public LayoutName getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(LayoutName layoutName) {
        this.layoutName = layoutName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Layout)) return false;
        Layout layout = (Layout) o;
        return Objects.equals(id, layout.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Layout{" +
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
