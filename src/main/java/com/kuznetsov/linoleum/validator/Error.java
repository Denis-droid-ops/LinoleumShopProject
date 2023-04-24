package com.kuznetsov.linoleum.validator;

public final class Error {
    private final String code;
    private final String message;

    public Error(String code,String message) {
        this.code = code;
        this.message=message;
    }
}
