package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dto.CreateFragmentDto;

public class CreateFragmentDtoValidator implements Validator<CreateFragmentDto> {
    private static final CreateFragmentDtoValidator INSTANCE = new CreateFragmentDtoValidator();

    private CreateFragmentDtoValidator(){}

    @Override
    public ValidationResult isValid(CreateFragmentDto object) {
        ValidationResult validationResult = new ValidationResult();

        if(object.getWidth().isEmpty() ){
            validationResult.addError(new Error("nullable.fragmentsWithoutLayout.fWidth","Width is null, please enter value!"));
        }else {
            if(Float.parseFloat(object.getWidth())>4){
                validationResult.addError(new Error("invalid.fragmentsWithoutLayout.fWidth","Width is invalid, please enter right value!"));
            }
        }

        if(object.getLength().isEmpty()){
            validationResult.addError(new Error("nullable.fragmentsWithoutLayout.fLength","Length is null, please enter value!"));
        }

        return validationResult;
    }

    public static CreateFragmentDtoValidator getInstance(){
        return INSTANCE;
    }
}

