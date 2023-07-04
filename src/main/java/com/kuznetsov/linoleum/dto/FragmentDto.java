package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.FragmentType;
import com.kuznetsov.linoleum.entity.LayoutName;

import java.util.Objects;

public final class FragmentDto {
    private final Integer id;
    private final Float width;
    private final Float length;
    private final FragmentType fType;
    private final LayoutName layoutName;

    public FragmentDto(Integer id, Float width, Float length, FragmentType fType, LayoutName layoutName) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.fType = fType;
        this.layoutName = layoutName;
    }

    public Integer getId() {
        return id;
    }

    public Float getWidth() {
        return width;
    }

    public Float getLength() {
        return length;
    }

    public FragmentType getfType() {
        return fType;
    }

    public LayoutName getLayoutName() {
        return layoutName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FragmentDto)) return false;
        FragmentDto that = (FragmentDto) o;
        return Objects.equals(id, that.id) && Objects.equals(width, that.width) && Objects.equals(length, that.length) && fType == that.fType && Objects.equals(layoutName, that.layoutName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, length, fType, layoutName);
    }

    @Override
    public String toString() {
        return "FragmentDto{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", fType=" + fType +
                ", layoutName=" + layoutName +
                '}';
    }
}
