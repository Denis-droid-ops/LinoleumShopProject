package com.kuznetsov.linoleum.dto;

import javax.servlet.http.Part;
import java.util.Objects;

public final class CreateLinoleumDto {
    private final String name;
    private final String protect;
    private final String thickness;
    private final String price;
    private final Part imagePath;


    public CreateLinoleumDto(String name, String protect, String thickness, String price, Part imagePath) {
        this.name = name;
        this.protect = protect;
        this.thickness = thickness;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getProtect() {
        return protect;
    }

    public String getThickness() {
        return thickness;
    }

    public String getPrice() {
        return price;
    }

    public Part getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateLinoleumDto)) return false;
        CreateLinoleumDto that = (CreateLinoleumDto) o;
        return Objects.equals(name, that.name) && Objects.equals(protect, that.protect) && Objects.equals(thickness, that.thickness) && Objects.equals(price, that.price) && Objects.equals(imagePath, that.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, protect, thickness, price, imagePath);
    }

    @Override
    public String toString() {
        return "CreateLinoleumDto{" +
                "name='" + name + '\'' +
                ", protect='" + protect + '\'' +
                ", thickness='" + thickness + '\'' +
                ", price='" + price + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
