package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateApartmentDto {
    private final String number;

    public CreateApartmentDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateApartmentDto)) return false;
        CreateApartmentDto that = (CreateApartmentDto) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "CreateApartmentDto{" +
                "number='" + number + '\'' +
                '}';
    }
}
