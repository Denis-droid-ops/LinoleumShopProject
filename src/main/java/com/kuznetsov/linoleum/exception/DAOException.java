package com.kuznetsov.linoleum.exception;

public class DAOException extends RuntimeException{
   public DAOException(Throwable throwable){
       super(throwable);
   }
}
