/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.exception;

import org.demosecurity.commons.exception.GeneralException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author joksin
 */
public class AuthenticationException extends GeneralException {
    
    public AuthenticationException(String message, HttpStatus httpStatus) {
        super(ERROR, message, httpStatus);
    }
    
}
