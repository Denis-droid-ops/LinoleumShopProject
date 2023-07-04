package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateLayoutNameDto {
    private final String lnName;

    public CreateLayoutNameDto(String lnName) {
        this.lnName = lnName;
    }

    public String getLnName() {
        return lnName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateLayoutNameDto)) return false;
        CreateLayoutNameDto that = (CreateLayoutNameDto) o;
        return Objects.equals(lnName, that.lnName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lnName);
    }

    @Override
    public String toString() {
        return "CreateLayoutNameDto{" +
                "lnName='" + lnName + '\'' +
                '}';
    }
}
