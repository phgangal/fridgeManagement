package com.fridgemanagement.exception;

import com.fridgemanagement.model.FridgeManagementErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * MyRetailExceptionHandler to handle all the exception responses
 */
public class FridgeManagementExceptionHandler {

    @ExceptionHandler(FridgeManagementException.class)
    public ResponseEntity<FridgeManagementErrorResponse> handleMyRetailException(FridgeManagementException ex) {
        FridgeManagementErrorResponse fridgeManagementErrorResponse = new FridgeManagementErrorResponse();
        fridgeManagementErrorResponse.setErrorMessage(ex.getMessage());
        fridgeManagementErrorResponse.setStatus(ex.getStatus());
        return new ResponseEntity<>(fridgeManagementErrorResponse, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FridgeManagementErrorResponse> handleGenericException(Exception ex) {
        FridgeManagementErrorResponse errorResponse = new FridgeManagementErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setErrorMessage("Internal Error :" + ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
