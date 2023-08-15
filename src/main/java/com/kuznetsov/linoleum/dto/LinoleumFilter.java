package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class LinoleumFilter {

    private final int maxPrice;
    private final int minPrice;

    public LinoleumFilter(int maxPrice, int minPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinoleumFilter)) return false;
        LinoleumFilter that = (LinoleumFilter) o;
        return maxPrice == that.maxPrice && minPrice == that.minPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxPrice, minPrice);
    }

    @Override
    public String toString() {
        return "LinoleumFilter{" +
                "maxPrice=" + maxPrice +
                ", minPrice=" + minPrice +
                '}';
    }
}
