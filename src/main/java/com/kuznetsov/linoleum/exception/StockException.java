package com.kuznetsov.linoleum.exception;

import com.kuznetsov.linoleum.dto.FragmentDto;
import com.kuznetsov.linoleum.validator.Error;

import java.util.List;

public class StockException extends RuntimeException{
    private final List<Error> errors;
    private final FragmentDto fragmentDto;

    public StockException(List<Error> errors, FragmentDto fragmentDto) {
        this.errors = errors;
        this.fragmentDto = fragmentDto;
    }

    public List<Error> getErrors(){
        return errors;
    }

    public FragmentDto getFragmentDto() {
        return fragmentDto;
    }
}
