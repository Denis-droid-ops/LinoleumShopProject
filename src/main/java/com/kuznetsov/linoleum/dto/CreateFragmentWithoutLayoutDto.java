package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class CreateFragmentWithoutLayoutDto {
    private final String fWidth;
    private final String fLength;
    private String orderId; //not final because needed setter

    public CreateFragmentWithoutLayoutDto(String fWidth, String fLength, String orderId) {
        this.fWidth = fWidth;
        this.fLength = fLength;
        this.orderId = orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getfWidth() {
        return fWidth;
    }

    public String getfLength() {
        return fLength;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateFragmentWithoutLayoutDto)) return false;
        CreateFragmentWithoutLayoutDto that = (CreateFragmentWithoutLayoutDto) o;
        return Objects.equals(fWidth, that.fWidth) && Objects.equals(fLength, that.fLength) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fWidth, fLength, orderId);
    }

    @Override
    public String toString() {
        return "CreateFragmentWithoutLayoutDto{" +
                "fWidth='" + fWidth + '\'' +
                ", fLength='" + fLength + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
