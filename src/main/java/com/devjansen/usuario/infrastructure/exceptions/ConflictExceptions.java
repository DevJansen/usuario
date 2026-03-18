package com.devjansen.usuario.infrastructure.exceptions;

public class ConflictExceptions extends RuntimeException{

    public ConflictExceptions(String message){
        super(message);
    }
    public ConflictExceptions(String message, Throwable cause){
        super(message, cause);
    }

}
