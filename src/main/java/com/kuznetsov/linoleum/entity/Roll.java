package com.kuznetsov.linoleum.entity;

import java.util.Objects;

public class Roll {
    private Integer id;
    private Integer partNum;
    private Float width;
    private Float length;
    private boolean isRemain;
    private Linoleum linoleum;


    public Roll() {
    }

    public Roll(Integer id, Integer partNum, Float width, Float length, boolean isRemain, Linoleum linoleum) {
        this.id = id;
        this.partNum = partNum;
        this.width = width;
        this.length = length;
        this.isRemain = isRemain;
        this.linoleum = linoleum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartNum() {
        return partNum;
    }

    public void setPartNum(Integer partNum) {
        this.partNum = partNum;
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

    public Linoleum getLinoleum() {
        return linoleum;
    }

    public void setLinoleum(Linoleum linoleum) {
        this.linoleum = linoleum;
    }

    public boolean isRemain() {
        return isRemain;
    }

    public void setRemain(boolean remain) {
        isRemain = remain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roll)) return false;
        Roll roll = (Roll) o;
        return Objects.equals(id, roll.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Roll{" +
                "id=" + id +
                ", partNum=" + partNum +
                ", width=" + width +
                ", length=" + length +
                ", isRemain=" + isRemain +
                ", linoleum=" + linoleum +
                '}';
    }
}
