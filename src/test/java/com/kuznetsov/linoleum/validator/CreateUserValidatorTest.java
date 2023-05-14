package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.util.ConnectionManager;
import com.kuznetsov.linoleum.util.InitDB;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

import static com.kuznetsov.linoleum.testData.CreateUserValidatorTestData.*;
import static com.kuznetsov.linoleum.testData.UserTestData.ALL_USERS;

@ExtendWith(MockitoExtension.class)
public class CreateUserValidatorTest {
    private final Logger logger = LoggerFactory.getLogger(CreateUserValidatorTest.class);

    @Mock
    private UserDao userDao;

    @InjectMocks
    private CreateUserValidator createUserValidator;

    @Test
    void isValidForNullable(){
        Mockito.doReturn(ALL_USERS).when(userDao).findAll();
        ValidationResult validationResult = createUserValidator.isValid(NULLABLE_USER_DTO);
        List<Error> actual = validationResult.getErrors();
        logger.info("Actual: {},\n expected: {}",actual, NULLABLE_ERRORS);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(NULLABLE_ERRORS);
    }

    @Test
    void isValidForNotUnique(){
        Mockito.doReturn(ALL_USERS).when(userDao).findAll();
        ValidationResult validationResult = createUserValidator.isValid(NOTUNIQUE_USER_DTO);
        List<Error> actual = validationResult.getErrors();
        logger.info("Actual: {},\n expected: {}",actual, NOTUNIQUE_ERRORS);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(NOTUNIQUE_ERRORS);
    }

    @Test
    void isValidForInvalid(){
        Mockito.doReturn(ALL_USERS).when(userDao).findAll();
        ValidationResult validationResult = createUserValidator.isValid(INVALID_USER_DTO);
        List<Error> actual = validationResult.getErrors();
        logger.info("Actual: {},\n expected: {}",actual, INVALID_ERRORS);
        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(INVALID_ERRORS);

    }
}
