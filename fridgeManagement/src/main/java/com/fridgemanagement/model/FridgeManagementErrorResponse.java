package com.fridgemanagement.model;

import org.springframework.http.HttpStatus;

/**
 * Pojo to hold Error Details
 */
public class FridgeManagementErrorResponse {

    private HttpStatus status;
    private String errorMessage;

    /**
     * Emplty Constructor
     */
    public FridgeManagementErrorResponse(HttpStatus status,String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructor
     * @return
     */
    public FridgeManagementErrorResponse(){}


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
