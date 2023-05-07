package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.testData.CreateUserValidatorTestData;
import org.junit.jupiter.api.Test;

public class CreateUserValidatorTest {
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    @Test
    void isValid(){
        createUserValidator.isValid(CreateUserValidatorTestData.CREATE_USER_DTO);

    }
}
