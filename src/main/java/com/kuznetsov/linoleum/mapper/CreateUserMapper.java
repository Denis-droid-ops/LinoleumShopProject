package com.kuznetsov.linoleum.mapper;

import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;

public class CreateUserMapper implements Mapper<CreateUserDto,User>{
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private CreateUserMapper(){}
    @Override
    public User mapFrom(CreateUserDto createUserDto) {
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        user.setPhoneNumber(Long.valueOf(createUserDto.getPhoneNumber()));
        user.setRole(Role.valueOf("USER"));   //the field is assigned on the server
        return user;
    }
    public static CreateUserMapper getInstance(){
        return INSTANCE;
    }
}
