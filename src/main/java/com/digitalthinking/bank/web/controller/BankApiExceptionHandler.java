/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.web.controller;

import com.digitalthinking.bank.exception.StandarizedApiErrorResponse;
import com.digitalthinking.bank.exception.BusinessRuleExceptions;
import com.digitalthinking.bank.exception.NoContentException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 *
 * OscarMayor
 */
@RestControllerAdvice//Indicate that this calss assit a controller class and can have a body in response
public class BankApiExceptionHandler {    
    //Allow define a method for handler this particular exception in transversal way, as a global exception handler
    @ExceptionHandler(BusinessRuleExceptions.class)
    public ResponseEntity<StandarizedApiErrorResponse> handleBusinessRuleExceptions(BusinessRuleExceptions ex) {
        StandarizedApiErrorResponse response = new StandarizedApiErrorResponse(ex.getCode(),"", ex.getMessage());
       return new ResponseEntity<>(response, ex.getHttpStatus() != null ? ex.getHttpStatus() : HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<StandarizedApiErrorResponse> handleNoContentException(NoContentException ex) {
        StandarizedApiErrorResponse response = new StandarizedApiErrorResponse("erorr-1204","",ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
     @ExceptionHandler(IOException.class)
    public ResponseEntity<StandarizedApiErrorResponse> handleNoContentException(IOException ex) {
        StandarizedApiErrorResponse response = new StandarizedApiErrorResponse("Input Ouput Error","erorr-1024",ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
