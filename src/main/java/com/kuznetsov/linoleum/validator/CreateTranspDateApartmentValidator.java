package com.kuznetsov.linoleum.validator;


import com.kuznetsov.linoleum.dto.CreateOrderDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class CreateTranspDateApartmentValidator implements Validator<CreateOrderDto> {
    private static final CreateTranspDateApartmentValidator INSTANCE = new CreateTranspDateApartmentValidator();

    private CreateTranspDateApartmentValidator(){

    }

    @Override
    public ValidationResult isValid(CreateOrderDto object){
        ValidationResult validationResult = new ValidationResult();

        if(object.getTransportingDate().isEmpty() || object.getTransportingDate().trim().isEmpty()){
            validationResult.addError(new Error("nullable.order.transportingDate","Transporting date is null, please enter value!"));
        }else {
            try {
                LocalDateTime.parse(object.getTransportingDate());
            } catch (DateTimeParseException e) {
                validationResult.addError(new Error("invalid.order.transportingDate","Transporting date is invalid, please enter right value!"));
            }
            if(LocalDateTime.parse(object.getTransportingDate()).isBefore(LocalDateTime.now())){
                validationResult.addError(new Error("invalid.order.transportingDate","Transporting date must be after than now, please enter right value!"));
            }
        }

        if(object.getApartmentNum().isEmpty() || object.getApartmentNum().trim().isEmpty()){
            validationResult.addError(new Error("nullable.order.apartmentNum","Apartment number is null, please enter value!"));
        }else {
            if (!object.getApartmentNum().matches("\\d+") || Integer.parseInt(object.getApartmentNum())>1000) {
                validationResult.addError(new Error("invalid.order.apartmentNum", "Apartment number is invalid, please enter right value"));
            }

        }

        return validationResult;
    }

    public static CreateTranspDateApartmentValidator getInstance(){
        return INSTANCE;
    }
}
