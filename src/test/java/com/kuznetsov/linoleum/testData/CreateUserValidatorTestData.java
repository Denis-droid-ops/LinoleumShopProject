package com.kuznetsov.linoleum.testData;

import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.validator.Error;

import java.util.ArrayList;
import java.util.List;

public final class CreateUserValidatorTestData{
    public static final Error ERROR_NULL_NAME = new Error("nullable.name","Name is null, please try again!");
    public static final Error ERROR_NULL_EMAIL = new Error("nullable.email","Email is null, please try again!");
    public static final Error ERROR_NULL_PASSWORD = new Error("nullable.password","Password is null, please try again!");
    public static final Error ERROR_NULL_PHONE_NUMBER = new Error("nullable.phoneNumber","PhoneNumber is null, please try again!");

    public static final Error ERROR_NOTUNIQUE_EMAIL = new Error("notUnique.email","This email already exists, please enter other email!");
    public static final Error ERROR_NOTUNIQUE_PHONE_NUMBER = new Error("notUnique.phoneNumber","This phone number already exists, please enter other email!");

    public static final Error ERROR_INVALID_PHONE_NUM = new Error("invalid.phoneNumber","PhoneNumber is invalid, please try again!");
    public static final Error ERROR_INVALID_EMAIL = new Error("invalid.email","Email is invalid , please try again!");

    public static final CreateUserDto CREATE_USER_DTO = new CreateUserDto("Jack","Jackkk4@mail.ru","555","89754548899");
    public static final CreateUserDto NULLABLE_USER_DTO = new CreateUserDto("","","","");
    public static final CreateUserDto NOTUNIQUE_USER_DTO = new CreateUserDto("Tom","denis.denis.kuznecov@mail.ru","1111","89179292245");
    public static final CreateUserDto INVALID_USER_DTO = new CreateUserDto("Tom","Tomm22","11111","9179292245");

    public static final List<Error> NULLABLE_ERRORS = new ArrayList<>(List.of(ERROR_NULL_NAME
    ,ERROR_NULL_EMAIL,ERROR_NULL_PASSWORD,ERROR_NULL_PHONE_NUMBER));
    public static final List<Error> NOTUNIQUE_ERRORS = new ArrayList<>(List.of(ERROR_NOTUNIQUE_EMAIL,ERROR_NOTUNIQUE_PHONE_NUMBER));
    public static final List<Error> INVALID_ERRORS = new ArrayList<>(List.of(ERROR_INVALID_EMAIL,ERROR_INVALID_PHONE_NUM));


}
