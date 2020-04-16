package com.fridgemanagement.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends FridgeManagementException {

    /**
     * Exception class for Fridge Not Found Exception
     */
    public UserNotFoundException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

}
