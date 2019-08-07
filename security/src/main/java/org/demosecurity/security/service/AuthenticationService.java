/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import javax.servlet.http.HttpServletRequest;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.model.Authentication;
import org.demosecurity.security.model.TokenDetails;

/**
 *
 * @author joksin
 */
public interface AuthenticationService {
    
    public TokenDetails obtainAccessToken(HttpServletRequest request) throws AuthenticationException;
    public Authentication authenticate(HttpServletRequest request) throws AuthenticationException;
    
}
