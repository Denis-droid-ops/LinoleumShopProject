package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class Fragment {
    private Integer id;
    private Float width;
    private Float length;
    private FragmentType fType;
    private LayoutName layoutName;

    public Fragment() {
    }

    public Fragment(Integer id, Float width, Float length, FragmentType fType, LayoutName layoutName) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.fType = fType;
        this.layoutName = layoutName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public FragmentType getfType() {
        return fType;
    }

    public void setfType(FragmentType fType) {
        this.fType = fType;
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
        if (!(o instanceof Fragment)) return false;
        Fragment fragment = (Fragment) o;
        return Objects.equals(id, fragment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Fragment{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", fType=" + fType +
                ", layoutName=" + layoutName +
                '}';
    }
}
