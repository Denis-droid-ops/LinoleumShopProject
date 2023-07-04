package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class Apartment {
    private Integer id;
    private Integer number;
    private Layout layout;

    public Apartment(){}

    public Apartment(Integer id, Integer number, Layout layout) {
        this.id = id;
        this.number = number;
        this.layout = layout;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartment)) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", number=" + number +
                ", layout=" + layout +
                '}';
    }
}
