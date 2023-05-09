package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.util.InitDB;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.kuznetsov.linoleum.testData.CreateUserValidatorTestData.*;

public class CreateUserValidatorTest {
    private final Logger logger = LoggerFactory.getLogger(CreateUserValidatorTest.class);
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    static void init(){
        ConnectionManager.enableConnForTests();
        InitDB.init();
    }

    @Test
    void isValidForNullable(){
        ValidationResult validationResult = createUserValidator.isValid(CREATE_USER_DTO);
        List<Error> actual = validationResult.getErrors();
        logger.info("Actual: {},\n expected: {}",actual, NULLABLE_ERRORS);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(NULLABLE_ERRORS);
    }

    @Test
    void isValidForNotUnique(){
        ValidationResult validationResult = createUserValidator.isValid(CREATE_USER_DTO);

    }
}
