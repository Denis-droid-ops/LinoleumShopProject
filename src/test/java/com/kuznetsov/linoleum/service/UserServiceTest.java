package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.testData.InitDB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class UserServiceTest {
    private UserService userService;

    @Test
    void save(){
        InitDB.init();

    }
}
