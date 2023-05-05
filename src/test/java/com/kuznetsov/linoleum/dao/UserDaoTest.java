package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.util.InitDB;

import static com.kuznetsov.linoleum.testData.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDaoTest {
    private final Logger logger = LoggerFactory.getLogger(UserDaoTest.class); 
    private UserDao userDao = UserDao.getInstance();

    @BeforeAll
    static void init(){

        ConnectionManager.enableConnForTests();
        InitDB.init();
    }

    @Test
    void save(){
        User actualUser = userDao.save(getNewUser());
        Integer actualUserId = actualUser.getId();
        User expectedUser = getNewUser();
        expectedUser.setId(actualUserId);
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    void findById(){
       User actualUser = userDao.findById(USER2_ID).get();
       assertThat(actualUser).isEqualTo(USER2);
    }

    @Test
    void findAll(){
        List<User> actualList = userDao.findAll();
        assertThat(actualList).isEqualTo(ALL_USERS);
    }

    @Test
    void update(){
        User expected = USER1;
        userDao.update(UPDATED_USER);
        assertThat(userDao.findById(USER1.getId())).isNotEqualTo(expected);
    }


}
