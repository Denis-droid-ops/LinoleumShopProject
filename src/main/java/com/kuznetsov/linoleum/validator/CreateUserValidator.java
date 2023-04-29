package com.kuznetsov.linoleum.validator;
import com.kuznetsov.linoleum.dao.UserDao;
import com.kuznetsov.linoleum.dto.CreateUserDto;
import com.kuznetsov.linoleum.entity.Role;


import java.util.stream.Collectors;

public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private final UserDao userDao = UserDao.getInstance();
    private CreateUserValidator(){

    }
    @Override
    public ValidationResult isValid(CreateUserDto object){
        ValidationResult validationResult = new ValidationResult();

        if(object.getName()==""){
            validationResult.addError(new Error("nullable.name","Name is null, please try again!"));
        }
        if(object.getEmail()==""){
            validationResult.addError(new Error("nullable.email","Email is null, please try again!"));
        }
      //  if(object.getEmail()!="" && !object.getEmail().matches("[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})")){
       //     validationResult.addError(new Error("invalid.email","Email is invalid , please try again!"));
      //  }
        if(userDao.findAll().stream().map(user -> user.getEmail()).collect(Collectors.toList()).contains(object.getEmail())){
            validationResult.addError(new Error("notUnique.email","This email already exists, please enter other email!"));
        }
        if(object.getPassword()==""){
            validationResult.addError(new Error("nullable.password","Password is null, please try again!"));
        }
        if(object.getPhoneNumber()!="" && !object.getPhoneNumber().matches("8\\d{10}")){
            validationResult.addError(new Error("invalid.phoneNumber","PhoneNumber is invalid, please try again!"));
        }
        if(object.getPhoneNumber()==""){
            validationResult.addError(new Error("nullable.phoneNumber","PhoneNumber is null, please try again!"));
        }
        if(userDao.findAll().stream().map(user -> user.getPhoneNumber()).collect(Collectors.toList()).contains(object.getPhoneNumber())){
            validationResult.addError(new Error("notUnique.phoneNumber","This phone number already exists, please enter other email!"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}
