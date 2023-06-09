package com.kuznetsov.linoleum.validator;

public final class Error {
    private final String code;
    private final String message;

    public Error(String code,String message) {
        this.code = code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
