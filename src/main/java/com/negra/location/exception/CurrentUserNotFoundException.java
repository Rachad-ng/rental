package com.negra.location.exception;

public class CurrentUserNotFoundException extends RuntimeException{

    public CurrentUserNotFoundException(String message){
        super(message);
    }

}
