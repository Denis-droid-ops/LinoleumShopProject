package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.Linoleum;

import java.util.Objects;

public final class RollDto {
    private final Integer id;
    private final Integer partNum;
    private final Float rWidth;
    private final Float rLength;
    private final boolean isRemain;
    private final Linoleum linoleum;

    public RollDto(Integer id, Integer partNum, Float rWidth, Float rLength, boolean isRemain, Linoleum linoleum) {
        this.id = id;
        this.partNum = partNum;
        this.rWidth = rWidth;
        this.rLength = rLength;
        this.isRemain = isRemain;
        this.linoleum = linoleum;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPartNum() {
        return partNum;
    }

    public Float getrWidth() {
        return rWidth;
    }

    public Float getrLength() {
        return rLength;
    }

    public boolean isRemain() {
        return isRemain;
    }

    public Linoleum getLinoleum() {
        return linoleum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RollDto)) return false;
        RollDto rollDto = (RollDto) o;
        return isRemain == rollDto.isRemain && Objects.equals(id, rollDto.id) && Objects.equals(partNum, rollDto.partNum) && Objects.equals(rWidth, rollDto.rWidth) && Objects.equals(rLength, rollDto.rLength) && Objects.equals(linoleum, rollDto.linoleum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partNum, rWidth, rLength, isRemain, linoleum);
    }

    @Override
    public String toString() {
        return "RollDto{" +
                "id=" + id +
                ", partNum=" + partNum +
                ", rWidth=" + rWidth +
                ", rLength=" + rLength +
                ", isRemain=" + isRemain +
                ", linoleum=" + linoleum +
                '}';
    }
}
