package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class Linoleum {
    private Integer id;
    private String name;
    private Float protect;
    private Float thickness;
    private Integer price;
    private String imagePath;

    public Linoleum() {
    }

    public Linoleum(Integer id, String name, Float protect, Float thickness, Integer price,String imagePath) {
        this.id = id;
        this.name = name;
        this.protect = protect;
        this.thickness = thickness;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getProtect() {
        return protect;
    }

    public void setProtect(Float protect) {
        this.protect = protect;
    }

    public Float getThickness() {
        return thickness;
    }

    public void setThickness(Float thickness) {
        this.thickness = thickness;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Linoleum)) return false;
        Linoleum linoleum = (Linoleum) o;
        return Objects.equals(id, linoleum.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Linoleum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", protect=" + protect +
                ", thickness=" + thickness +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
