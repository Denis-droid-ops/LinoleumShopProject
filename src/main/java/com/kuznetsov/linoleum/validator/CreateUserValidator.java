package com.kuznetsov.linoleum.validator;



import com.kuznetsov.linoleum.dto.UpdateUserRoleDto;
import com.kuznetsov.linoleum.dto.UserDto;
import com.kuznetsov.linoleum.entity.Role;
import com.kuznetsov.linoleum.entity.User;

public class CreateUserValidator implements Validator<UpdateUserRoleDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    private CreateUserValidator(){

    }
    @Override
    public ValidationResult isValid(UpdateUserRoleDto object){
        ValidationResult validationResult = new ValidationResult();
        if(Role.find(object.getRole())==null){
            validationResult.addError(new Error("invalid.role","Role is invalid or null,please try again!"));
        }
        return validationResult;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}
