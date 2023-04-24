package com.kuznetsov.linoleum.dto;

import com.kuznetsov.linoleum.entity.Role;

import java.util.Objects;

public final class UserDto {
   private final Integer id;
   private final String name;
   private final String email;
   private final Long phoneNumber;
   private final Role role;


    public UserDto(Integer id,String name, String email,Long phoneNumber, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name) && Objects.equals(email, userDto.email) && Objects.equals(phoneNumber, userDto.phoneNumber) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", role=" + role +
                '}';
    }
}
