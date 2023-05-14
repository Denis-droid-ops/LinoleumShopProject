package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;
import com.kuznetsov.linoleum.exception.ValidationException;
import com.kuznetsov.linoleum.testData.CreateUserValidatorTestData;
import com.kuznetsov.linoleum.testData.UserTestData;
import com.kuznetsov.linoleum.validator.CreateUserValidator;
import com.kuznetsov.linoleum.validator.Error;
import com.kuznetsov.linoleum.validator.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.kuznetsov.linoleum.testData.CreateUserValidatorTestData.CREATE_USER_DTO;
import static com.kuznetsov.linoleum.testData.UserTestData.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Mock
    private UserDao userDao;

    @Mock
    private CreateUserValidator createUserValidator;

    @InjectMocks
    private UserService userService;

    @Test
    void save(){
        Mockito.doReturn(new ValidationResult()).when(createUserValidator)
                .isValid(Mockito.any());
        Mockito.doReturn(new User(5,"Jack","Jackkk4@mail.ru","555",89754548899L, Role.USER))
                        .when(userDao).save(Mockito.any());
        User actual = userService.save(CREATE_USER_DTO);
        logger.info("Actual: {}, \n expected: {}",actual,USER5);
        assertThat(actual).usingRecursiveComparison().isEqualTo(UserTestData.USER5);
    }

    @Test
    void saveIfUserNotValid(){
        ValidationResult validationResult = new ValidationResult();
        validationResult.addError(new Error("Test error","User is not valid"));
        Mockito.doReturn(validationResult).when(createUserValidator)
                .isValid(Mockito.any());
        assertThrows(ValidationException.class, ()->userService.save(CREATE_USER_DTO));
    }

    @Test
    void login(){
        Mockito.doReturn(LOGGED_USER).when(userDao).findByEmailAndPassword("denis.denis.kuznecov@mail.ru","222");
        Optional<UserDto> actual = userService.login("denis.denis.kuznecov@mail.ru","222");
        logger.info("Actual: {}, \n expected: {}",actual.get(),USER1_DTO);
        assertThat(actual.get()).usingRecursiveComparison().isEqualTo(USER1_DTO);
    }
    @Test
    void findAll(){
        List<UserDto> expected = ALL_USERS.stream().map(user -> new UserDto(user.getId()
                ,user.getName(),user.getEmail(),user.getPhoneNumber(),user.getRole())).collect(Collectors.toList());
        Mockito.doReturn(ALL_USERS)
                .when(userDao).findAll();
        List<UserDto> actual = userService.findAll();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void updateRole(){
        Mockito.doNothing().when(userDao).updateRole(USER2.getId(),USER2.getRole());
        assertDoesNotThrow(()->userService.updateRole(new UpdateUserRoleDto(USER2_ID.toString(),USER2.getRole().toString())));
    }

    @Test
    void delete(){
        Mockito.doReturn(true).when(userDao).delete(Mockito.any());
        boolean actual = userService.delete(USER3_ID);
        assertThat(actual).isTrue();
    }

}
