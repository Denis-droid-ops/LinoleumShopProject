package com.kuznetsov.linoleum.validator;

public interface Validator<T>{
    ValidationResult isValid(T object);
}
