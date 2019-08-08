/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.model.Authentication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author joksin
 */
@Service
@ConditionalOnProperty(name = "security.authorization.type", havingValue = "none")
public class NoneAuthorizationService implements AuthorizationService {

    @Override
    public Authentication authorize(Authentication authentication) throws AuthenticationException {
        return authentication;
    }
    
}
