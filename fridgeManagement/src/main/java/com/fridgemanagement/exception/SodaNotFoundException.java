package com.fridgemanagement.exception;

import org.springframework.http.HttpStatus;

public class SodaNotFoundException extends FridgeManagementException {

    /**
     * Exception class for Fridge Not Found Exception
     */
    public SodaNotFoundException(String errorMessage, HttpStatus status) {
        super(errorMessage, status);
    }

}
