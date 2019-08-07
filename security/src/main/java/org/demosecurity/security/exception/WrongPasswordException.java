/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author joksin
 */
public class WrongPasswordException extends AuthenticationException {
    
    public WrongPasswordException() {
        super("Wrong password", HttpStatus.UNAUTHORIZED);
    }
    
}
