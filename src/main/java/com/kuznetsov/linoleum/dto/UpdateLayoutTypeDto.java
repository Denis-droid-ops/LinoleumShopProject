package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class UpdateLayoutTypeDto {
    private final String id;
    private final String layoutType;

    public UpdateLayoutTypeDto(String id, String layoutType) {
        this.id = id;
        this.layoutType = layoutType;
    }

    public String getId() {
        return id;
    }

    public String getLayoutType() {
        return layoutType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateLayoutTypeDto)) return false;
        UpdateLayoutTypeDto that = (UpdateLayoutTypeDto) o;
        return Objects.equals(id, that.id) && Objects.equals(layoutType, that.layoutType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, layoutType);
    }

    @Override
    public String toString() {
        return "UpdateLayoutTypeDto{" +
                "id='" + id + '\'' +
                ", layoutType='" + layoutType + '\'' +
                '}';
    }
}
