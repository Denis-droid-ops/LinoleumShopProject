package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.mapper.CreateUserMapper;
import com.kuznetsov.linoleum.mapper.UserDtoMapper;
import com.kuznetsov.linoleum.validator.CreateUserValidator;
import com.kuznetsov.linoleum.validator.ValidationResult;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserDtoMapper userDtoMapper = UserDtoMapper.getInstance();


    private UserService(){
    }

    public Optional<UserDto> login(String email,String password){
        return userDao.findByEmailAndPassword(email,password).map(userDtoMapper::mapFrom);
    }

    public User save(CreateUserDto createUserDto){
        ValidationResult validationResult = createUserValidator.isValid(createUserDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        User user = createUserMapper.mapFrom(createUserDto);
        return userDao.save(user);
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
