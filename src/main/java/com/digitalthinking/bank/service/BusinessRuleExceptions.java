/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalthinking.bank.service;

/**
 *
 * OscarMayor
 */
public class BusinessRuleExceptions extends RuntimeException{
    
    public BusinessRuleExceptions() {
        super();
    }
    public BusinessRuleExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public BusinessRuleExceptions(String message) {
        super(message);
    }
    public BusinessRuleExceptions(Throwable cause) {
        super(cause);
    }
    
}
