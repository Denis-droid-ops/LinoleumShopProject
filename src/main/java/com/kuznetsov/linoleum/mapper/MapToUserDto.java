package com.kuznetsov.linoleum.mapper;


import com.kuznetsov.linoleum.entity.User;

public class MapToUserDto implements Mapper<User, User>{
    private static final MapToUserDto INSTANCE = new MapToUserDto();

    private MapToUserDto(){};

    @Override
    public User mapFrom(User object) {
        return null;
    }

    public static MapToUserDto getInstance(){
        return INSTANCE;
    }
}
