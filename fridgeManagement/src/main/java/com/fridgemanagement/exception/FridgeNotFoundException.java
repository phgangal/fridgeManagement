package com.fridgemanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception class for Fridge Not Found Exception
 */
public class FridgeNotFoundException extends FridgeManagementException {

    public FridgeNotFoundException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

}
