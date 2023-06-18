package com.nemo.userservice.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound (String message){
        super(message);
    }

}
