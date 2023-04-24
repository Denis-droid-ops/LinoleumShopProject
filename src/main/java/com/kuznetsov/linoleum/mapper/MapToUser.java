package com.kuznetsov.linoleum.mapper;



import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;


public class MapToUser implements Mapper<UpdateUserRoleDto, User> {
    private static final MapToUser INSTANCE = new MapToUser();
    private MapToUser(){

    }
    @Override
    public User mapFrom(UpdateUserRoleDto object) {

        return null;
    }

    public static MapToUser getInstance(){
        return INSTANCE;
    }
}
