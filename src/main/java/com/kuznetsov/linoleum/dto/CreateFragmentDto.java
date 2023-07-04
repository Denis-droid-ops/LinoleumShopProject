package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateFragmentDto {
    private final String width;
    private final String length;
    private final String fType;
    private String layoutNameId; //not final because needed setter

    public CreateFragmentDto(String width, String length, String fType, String layoutNameId) {
        this.width = width;
        this.length = length;
        this.fType = fType;
        this.layoutNameId = layoutNameId;
    }

    public void setLayoutNameId(String layoutNameId) {
        this.layoutNameId = layoutNameId;
    }

    public String getWidth() {
        return width;
    }

    public String getLength() {
        return length;
    }

    public String getfType() {
        return fType;
    }

    public String getLayoutNameId() {
        return layoutNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateFragmentDto)) return false;
        CreateFragmentDto that = (CreateFragmentDto) o;
        return Objects.equals(width, that.width) && Objects.equals(length, that.length) && Objects.equals(fType, that.fType) && Objects.equals(layoutNameId, that.layoutNameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, length, fType, layoutNameId);
    }

    @Override
    public String toString() {
        return "CreateFragmentDto{" +
                "width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", fType='" + fType + '\'' +
                ", layoutNameId='" + layoutNameId + '\'' +
                '}';
    }
}
