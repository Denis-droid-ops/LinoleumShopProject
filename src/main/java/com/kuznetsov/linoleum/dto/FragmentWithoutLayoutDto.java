package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.Order;

import java.util.Objects;

public final class FragmentWithoutLayoutDto {
    private final Integer id;
    private final Float fWidth;
    private final Float fLength;
    private final Order order;

    public FragmentWithoutLayoutDto(Integer id, Float fWidth, Float fLength, Order order) {
        this.id = id;
        this.fWidth = fWidth;
        this.fLength = fLength;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public Float getfWidth() {
        return fWidth;
    }

    public Float getfLength() {
        return fLength;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FragmentWithoutLayoutDto)) return false;
        FragmentWithoutLayoutDto that = (FragmentWithoutLayoutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(fWidth, that.fWidth) && Objects.equals(fLength, that.fLength) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fWidth, fLength, order);
    }

    @Override
    public String toString() {
        return "FragmentWithoutLayoutDto{" +
                "id=" + id +
                ", fWidth=" + fWidth +
                ", fLength=" + fLength +
                ", order=" + order +
                '}';
    }
}
