package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class UpdateFragmentDto {
    private final String id;
    private final String width;
    private final String length;
    private final String fType;
    private final String layoutNameId;

    public UpdateFragmentDto(String id, String width, String length, String fType, String layoutNameId) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.fType = fType;
        this.layoutNameId = layoutNameId;
    }

    public String getId() {
        return id;
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
        if (!(o instanceof UpdateFragmentDto)) return false;
        UpdateFragmentDto that = (UpdateFragmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(width, that.width) && Objects.equals(length, that.length) && Objects.equals(fType, that.fType) && Objects.equals(layoutNameId, that.layoutNameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, length, fType, layoutNameId);
    }

    @Override
    public String toString() {
        return "UpdateFragmentDto{" +
                "id='" + id + '\'' +
                ", width='" + width + '\'' +
                ", length='" + length + '\'' +
                ", fType='" + fType + '\'' +
                ", layoutNameId='" + layoutNameId + '\'' +
                '}';
    }
}
