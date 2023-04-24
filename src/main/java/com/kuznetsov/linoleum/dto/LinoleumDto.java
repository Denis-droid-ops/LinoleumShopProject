package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class LinoleumDto {
    private final Integer id;
    private final String name;
    private final Float protect;
    private final Float thickness;
    private final Integer price;
    private final String imagePath;

    public LinoleumDto(Integer id, String name, Float protect, Float thickness, Integer price,String imagePath) {
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

    public String getName() {
        return name;
    }

    public Float getProtect() {
        return protect;
    }

    public Float getThickness() {
        return thickness;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinoleumDto)) return false;
        LinoleumDto that = (LinoleumDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(protect, that.protect) && Objects.equals(thickness, that.thickness) && Objects.equals(price, that.price) && Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, protect, thickness, price, imagePath);
    }

    @Override
    public String toString() {
        return "LinoleumDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", protect=" + protect +
                ", thickness=" + thickness +
                ", price=" + price +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
