package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class LayoutName {
    private Integer id;
    private String lnName;

    public LayoutName(){}

    public LayoutName(Integer id, String lnName) {
        this.id = id;
        this.lnName = lnName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLnName() {
        return lnName;
    }

    public void setLnName(String lnName) {
        this.lnName = lnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LayoutName)) return false;
        LayoutName that = (LayoutName) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LayoutName{" +
                "id=" + id +
                ", lnName='" + lnName + '\'' +
                '}';
    }
}
