package com.kuznetsov.linoleum.entity;

public class Roll {
    private Integer id;
    private Integer partNum;
    private Float width;
    private Float length;
    private Linoleum linoleum;

    public Roll() {
    }

    public Roll(Integer id, Integer partNum, Float width, Float length, Linoleum linoleum) {
        this.id = id;
        this.partNum = partNum;
        this.width = width;
        this.length = length;
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

    @Override
    public String toString() {
        return "Roll{" +
                "id=" + id +
                ", partNum=" + partNum +
                ", width=" + width +
                ", length=" + length +
                ", linoleum=" + linoleum +
                '}';
    }
}
