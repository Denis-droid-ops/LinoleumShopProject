package com.kuznetsov.linoleum.entity;

public class Address {
    private Integer id;
    private String city;
    private String street;
    private Integer homeNum;
    private Integer apartmentNum;
    private Layout layout;

    public Address() {
    }

    public Address(Integer id, String city, String street, Integer homeNum, Integer apartmentNum, Layout layout) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.homeNum = homeNum;
        this.apartmentNum = apartmentNum;
        this.layout = layout;
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

    public Integer getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(Integer homeNum) {
        this.homeNum = homeNum;
    }

    public Integer getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(Integer apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", homeNum=" + homeNum +
                ", apartmentNum=" + apartmentNum +
                ", layout=" + layout +
                '}';
    }
}
