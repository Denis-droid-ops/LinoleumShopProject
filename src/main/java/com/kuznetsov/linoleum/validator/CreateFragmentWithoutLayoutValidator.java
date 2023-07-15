package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dto.CreateFragmentWithoutLayoutDto;


public class CreateFragmentWithoutLayoutValidator implements Validator<CreateFragmentWithoutLayoutDto> {
    private static final CreateFragmentWithoutLayoutValidator INSTANCE = new CreateFragmentWithoutLayoutValidator();

    private CreateFragmentWithoutLayoutValidator(){

    }
    @Override
    public ValidationResult isValid(CreateFragmentWithoutLayoutDto object){
        ValidationResult validationResult = new ValidationResult();

        if(object.getfWidth().isEmpty() || object.getfWidth().trim().isEmpty()){
            validationResult.addError(new Error("nullable.fragmentsWithoutLayout.fWidth","Width is null, please enter value!"));
        }else {
            if(Float.parseFloat(object.getfWidth())>4){
                validationResult.addError(new Error("invalid.fragmentsWithoutLayout.fWidth","Width is invalid, please enter right value!"));
            }
        }

        if(object.getfLength().isEmpty() || object.getfLength().trim().isEmpty()){
            validationResult.addError(new Error("nullable.fragmentsWithoutLayout.fLength","Length is null, please enter value!"));
        }

        return validationResult;
    }

    public static CreateFragmentWithoutLayoutValidator getInstance(){
        return INSTANCE;
    }
}
