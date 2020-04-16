package com.fridgemanagement.exception;

import org.springframework.http.HttpStatus;

/**
 * Generic Exception class
 */
public class FridgeManagementException extends Exception {

    private String errorMessage;
    private HttpStatus status;

    public FridgeManagementException(String errorMessage, HttpStatus status) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public FridgeManagementException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
