package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.User;

public class UserDtoMapper implements Mapper<User, UserDto>{
    private static final UserDtoMapper INSTANCE = new UserDtoMapper();

    private UserDtoMapper(){}

    @Override
    public UserDto mapFrom(User object) {
        return new UserDto(object.getId()
        ,object.getName()
        ,object.getEmail()
        ,object.getPhoneNumber()
        ,object.getRole());
    }
    public static UserDtoMapper getInstance(){
        return INSTANCE;
    }
}
