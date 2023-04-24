package com.kuznetsov.linoleum.dto;

import java.util.Objects;

public final class UpdateUserRoleDto {
    private final String id;
    private final String role;

    public UpdateUserRoleDto(String id, String role) {
        this.id = id;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpdateUserRoleDto)) return false;
        UpdateUserRoleDto that = (UpdateUserRoleDto) o;
        return Objects.equals(id, that.id) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "UpdateUserRoleDto{" +
                "id='" + id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
