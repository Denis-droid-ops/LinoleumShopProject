package com.kuznetsov.linoleum.validator;


import com.kuznetsov.linoleum.dto.CreateLayoutDto;


public class CreateLayoutValidator implements Validator<CreateLayoutDto> {
    private static final CreateLayoutValidator INSTANCE = new CreateLayoutValidator();

    private CreateLayoutValidator(){

    }
    @Override
    public ValidationResult isValid(CreateLayoutDto object){
        ValidationResult validationResult = new ValidationResult();

        if(object.getCity().isEmpty() || object.getCity().trim().isEmpty()){
            validationResult.addError(new Error("nullable.layout.city","City is null, please enter value!"));
        }else {
            if (!object.getCity().matches("[A-Za-z]+")) {
                validationResult.addError(new Error("invalid.layout.city", "City is invalid, please enter right value!"));
            }
        }

        if(object.getStreet().isEmpty() || object.getStreet().trim().isEmpty()){
            validationResult.addError(new Error("nullable.layout.street","Street is null, please enter value!"));
        }else {
            if(!object.getStreet().matches("[a-zA-Z0-9]*")){
                validationResult.addError(new Error("invalid.layout.street","Street is invalid, please enter right value!"));
            }
        }

        if(object.getHomeNum().isEmpty() || object.getCity().trim().isEmpty()){
            validationResult.addError(new Error("nullable.layout.homeNum","Home number is null, please enter value!"));
        }

        if(object.getRoomCount().isEmpty() || object.getCity().trim().isEmpty()){
            validationResult.addError(new Error("nullable.layout.roomCount","Room count is null, please enter value!"));
        }else {
            if (!object.getRoomCount().matches("\\d+") || Integer.parseInt(object.getRoomCount())>10) {
                validationResult.addError(new Error("invalid.layout.roomCount", "Room count is invalid, please enter right value!"));
            }
        }


        return validationResult;
    }

    public static CreateLayoutValidator getInstance(){
        return INSTANCE;
    }
}
