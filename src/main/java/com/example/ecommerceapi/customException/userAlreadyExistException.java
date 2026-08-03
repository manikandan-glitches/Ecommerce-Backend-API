package com.example.ecommerceapi.customException;

import org.springframework.stereotype.Component;
import java.lang.String;

public class userAlreadyExistException extends RuntimeException{
    public userAlreadyExistException(String message){
        super(message);
    }

}
