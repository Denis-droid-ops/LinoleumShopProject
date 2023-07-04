package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class FragmentWithoutLayout {
    private Integer id;
    private Float fWidth;
    private Float fLength;
    private Order order;

    public FragmentWithoutLayout(){}

    public FragmentWithoutLayout(Integer id, Float fWidth, Float fLength, Order order) {
        this.id = id;
        this.fWidth = fWidth;
        this.fLength = fLength;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getfWidth() {
        return fWidth;
    }

    public void setfWidth(Float fWidth) {
        this.fWidth = fWidth;
    }

    public Float getfLength() {
        return fLength;
    }

    public void setfLength(Float fLength) {
        this.fLength = fLength;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FragmentWithoutLayout)) return false;
        FragmentWithoutLayout that = (FragmentWithoutLayout) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FragmentWithoutLayout{" +
                "id=" + id +
                ", fWidth=" + fWidth +
                ", fLength=" + fLength +
                ", order=" + order +
                '}';
    }
}
