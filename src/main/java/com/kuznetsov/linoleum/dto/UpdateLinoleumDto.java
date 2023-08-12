package com.kuznetsov.linoleum.dto;

import javax.servlet.http.Part;
import java.util.Objects;

public final class UpdateLinoleumDto {
    private final String id;
    private final String name;
    private final String protect;
    private final String thickness;
    private final String price;
    private Part imagePath;
    private String defaultImagePath;

    public UpdateLinoleumDto(String id, String name, String protect, String thickness, String price, Part imagePath) {
        this.id = id;
        this.name = name;
        this.protect = protect;
        this.thickness = thickness;
        this.price = price;
        this.imagePath = imagePath;
    }

    public UpdateLinoleumDto(String id, String name, String protect, String thickness, String price, String defaultImagePath) {
        this.id = id;
        this.name = name;
        this.protect = protect;
        this.thickness = thickness;
        this.price = price;
        this.defaultImagePath = defaultImagePath;
    }

    public String getId() {
        return id;
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

    public String getDefaultImagePath() {
        return defaultImagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateLinoleumDto)) return false;
        UpdateLinoleumDto that = (UpdateLinoleumDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(protect, that.protect) && Objects.equals(thickness, that.thickness) && Objects.equals(price, that.price) && Objects.equals(imagePath, that.imagePath) && Objects.equals(defaultImagePath, that.defaultImagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, protect, thickness, price, imagePath, defaultImagePath);
    }

    @Override
    public String toString() {
        return "UpdateLinoleumDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", protect='" + protect + '\'' +
                ", thickness='" + thickness + '\'' +
                ", price='" + price + '\'' +
                ", imagePath=" + imagePath +
                ", defaultImagePath='" + defaultImagePath + '\'' +
                '}';
    }
}
