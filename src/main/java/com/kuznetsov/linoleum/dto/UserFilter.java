package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class UserFilter {
    private int limit;
    private int offset;
    private Long phoneNumber;

    public UserFilter(int limit, int offset, Long phoneNumber) {
        this.limit = limit;
        this.offset = offset;
        this.phoneNumber = phoneNumber;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFilter)) return false;
        UserFilter that = (UserFilter) o;
        return limit == that.limit && offset == that.offset && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, offset, phoneNumber);
    }
}
