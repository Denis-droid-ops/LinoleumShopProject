package com.kuznetsov.linoleum.testData;

import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.validator.Error;

import java.util.ArrayList;
import java.util.List;

public final class CreateUserValidatorTestData{
    public static final Error ERROR_NULL_NAME = new Error("nullable.name","Name is null, please try again!");
    public static final Error ERROR_NULL_EMAIL = new Error("nullable.email","Email is null, please try again!");
    public static final CreateUserDto CREATE_USER_DTO = new CreateUserDto("","","","");
}
