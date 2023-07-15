package com.kuznetsov.linoleum.validator;

import com.kuznetsov.linoleum.dto.CreateDeliveryAddressDto;

public class CreateDeliveryAddressValidator implements Validator<CreateDeliveryAddressDto> {
    private static final CreateDeliveryAddressValidator INSTANCE = new CreateDeliveryAddressValidator();

    private CreateDeliveryAddressValidator(){

    }
    @Override
    public ValidationResult isValid(CreateDeliveryAddressDto object){
        ValidationResult validationResult = new ValidationResult();

        if(object.getdCity().isEmpty() || object.getdCity().trim().isEmpty()){
            validationResult.addError(new Error("nullable.deliveryAddress.dCity","City is null, please enter value!"));
        }else {
            if(!object.getdCity().matches("[a-zA-Z]+")){
                validationResult.addError(new Error("invalid.deliveryAddress.dCity","City is invalid, please enter right value!"));
            }
        }

        if(object.getdStreet().isEmpty() || object.getdStreet().trim().isEmpty()){
            validationResult.addError(new Error("nullable.deliveryAddress.dStreet","Street is null, please enter value!"));
        }else {
            if(!object.getdStreet().matches("[a-zA-Z0-9]*")){
                validationResult.addError(new Error("invalid.deliveryAddress.dStreet","Street is invalid, please enter right value!"));
            }
        }

        if(object.getdHomeNum().isEmpty()){
            validationResult.addError(new Error("nullable.deliveryAddress.dHomeNum","Home number is null, please enter value"));
        }


        return validationResult;
    }

    public static CreateDeliveryAddressValidator getInstance(){
        return INSTANCE;
    }
}
