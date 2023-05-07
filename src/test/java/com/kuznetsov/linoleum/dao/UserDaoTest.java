package com.kuznetsov.linoleum.dao;

import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;

import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.util.InitDB;

import static com.kuznetsov.linoleum.testData.UserTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.*;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(OrderAnnotation.class)
public class UserDaoTest {
    private final Logger logger = LoggerFactory.getLogger(UserDaoTest.class); 
    private final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    static void init(){
        ConnectionManager.enableConnForTests();
        InitDB.init();
    }

    @Test
    @Order(3)
    void save(){
        User actualUser = userDao.save(getNewUser());
        Integer actualUserId = actualUser.getId();
        User expectedUser = getNewUser();
        expectedUser.setId(actualUserId);
        logger.info("Actual: {}, expected: {}",actualUser,expectedUser);
        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);
    }

    @Test
    @Order(2)
    void findById(){
       User actualUser = userDao.findById(USER2_ID).get();
       logger.info("Actual: {}, expected: {}",actualUser,USER2);
       assertThat(actualUser).usingRecursiveComparison().isEqualTo(USER2);
    }

    @Test
    @Order(1)
    void findAll(){
        List<User> actualList = userDao.findAll();
        logger.info("Actual: {}, expected: {}",actualList,ALL_USERS);
        assertThat(actualList).usingRecursiveComparison().isEqualTo(ALL_USERS);
    }

    @Test
    @Order(4)
    void update(){
        User expected = USER1;
        userDao.update(UPDATED_USER1);
        logger.info("Actual: {}, expected: {}",userDao.findById(USER1.getId()).orElse(null),expected);
        assertThat(userDao.findById(USER1.getId()).orElse(null)).usingRecursiveComparison().isNotEqualTo(expected);
    }

    @Test
    @Order(5)
    void delete(){
        userDao.delete(USER3_ID);
        logger.info("Actual:{}, expected: {}",userDao.findById(USER3_ID).orElse(null),USER3);
        assertThat(userDao.findById(USER3_ID).orElse(null)).usingRecursiveComparison().isNotEqualTo(USER3);
    }

    @Test
    @Order(6)
    void findByEmailAndPasswordIfExists(){
        Optional<User> actual = userDao.findByEmailAndPassword(USER4.getEmail(),USER4.getPassword());
        logger.info("Actual: {}, Expected {}",actual,USER4);
        assertThat(actual.orElse(null)).usingRecursiveComparison().isEqualTo(USER4);
    }

    @Test
    @Order(7)
    void updateRole(){
        userDao.updateRole(USER4.getId(), Role.ADMIN);
        logger.info("Actual: {}, Expected: {}",userDao.findById(USER4_ID).orElse(null),USER4_WITH_UPDATED_ROLE);
        assertThat(userDao.findById(USER4_ID).orElse(null)).usingRecursiveComparison()
                .isEqualTo(USER4_WITH_UPDATED_ROLE);
    }



}
