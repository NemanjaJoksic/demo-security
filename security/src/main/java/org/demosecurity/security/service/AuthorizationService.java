/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.model.Authentication;

/**
 *
 * @author joksin
 */
public interface AuthorizationService {
    
    public Authentication authorize(Authentication authentication) throws AuthenticationException;
    
}
