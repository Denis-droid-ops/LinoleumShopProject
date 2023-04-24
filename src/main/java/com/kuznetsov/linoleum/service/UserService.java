package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.mapper.MapToUser;
import com.kuznetsov.linoleum.mapper.MapToUserDto;
import com.kuznetsov.linoleum.validator.CreateUserValidator;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final MapToUserDto mapToUserDto = MapToUserDto.getInstance();
    private final MapToUser mapToUser = MapToUser.getInstance();

    private UserService(){
    }

    public UserDto save(UserDto userDto){
        return null;
    }

    public void updateRole(UpdateUserRoleDto updateUserRoleDto){
        userDao.updateRole(Integer.valueOf(updateUserRoleDto.getId()),Role.valueOf(updateUserRoleDto.getRole()));
    }



    public boolean delete(Integer id){
       return userDao.delete(id);
    }

    public List<UserDto> findAll(){
        return userDao.findAll().stream().map(user->new UserDto(user.getId(),user.getName()
        ,user.getEmail(),user.getPhoneNumber(),user.getRole())).collect(Collectors.toList());
    }

    public static UserService getInstance(){
        return INSTANCE;
    }
}
