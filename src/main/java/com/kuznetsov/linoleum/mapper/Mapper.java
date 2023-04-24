package com.kuznetsov.linoleum.mapper;

public interface Mapper<F,T>{
    T mapFrom(F object);
}
