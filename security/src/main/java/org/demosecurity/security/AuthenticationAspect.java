/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.demosecurity.commons.exception.GeneralException;
import org.demosecurity.security.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author joksin
 */
@Component
@Aspect
public class AuthenticationAspect {
    
    private static final String CONTROLLER_JOIN_POINT = "execution(* org.demosecurity..*Controller**.*(..))";

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationAspect.class);
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @Around(value = CONTROLLER_JOIN_POINT)
    public ResponseEntity checkAuthentication(ProceedingJoinPoint joinPoint) throws GeneralException, Throwable {
        logger.info("Checking authentication");
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        
        String uri = request.getRequestURI();
        if(uri.equals("/login") || uri.equals("/register"))
            return (ResponseEntity) joinPoint.proceed();
        
        SecurityContext.setAuthentication(authenticationService.authenticate(request));
        return (ResponseEntity) joinPoint.proceed();
    }
    
}
