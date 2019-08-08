/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import org.demosecurity.security.crypto.PasswordEncoder;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.exception.InvalidAuthorizationHeaderException;
import org.demosecurity.security.exception.MissingAuthorizationHeaderException;
import org.demosecurity.security.exception.UserNotExistException;
import org.demosecurity.security.exception.WrongPasswordException;
import org.demosecurity.security.model.Authentication;
import org.demosecurity.security.model.TokenDetails;
import org.demosecurity.security.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author joksin
 */
public abstract class TokenAuthenticationService implements AuthenticationService {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    
    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public TokenDetails obtainAccessToken(HttpServletRequest request) throws AuthenticationException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);

        if (authorization != null) {
            String[] credentials = extractCredentialsFromAuthorizationHeader(authorization);
            User user = userService.loadUserByUsername(credentials[0]);

            if (user != null) {

                if (!passwordEncoder.matches(credentials[1], user.getPassword()))
                    throw new WrongPasswordException();

                return generateToken(user);
            } else {
                throw new UserNotExistException(credentials[0]);
            }

        } else {
            throw new MissingAuthorizationHeaderException();
        }
    }

    @Override
    public Authentication authenticate(HttpServletRequest request) throws AuthenticationException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null) {
            logger.debug(authorizationHeader);
            Authentication authentication = decodeToken(authorizationHeader.split(TokenDetails.BEARER + " ")[1]);
            authentication.setPath(request.getServletPath());
            authentication.setMethod(request.getMethod());
            logger.debug("{}", authentication);
            return authentication;
        } else {
            throw new MissingAuthorizationHeaderException();
        }
    }
    
    protected String[] extractCredentialsFromAuthorizationHeader(String authorization) throws AuthenticationException {

        if (!authorization.toLowerCase().startsWith("basic"))
            throw new InvalidAuthorizationHeaderException();

        String[] split = authorization.split(" ");
        if (split.length != 2)
            throw new InvalidAuthorizationHeaderException();

        byte[] credDecoded = Base64.getDecoder().decode(split[1]);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        return credentials.split(":");

    }

    protected abstract TokenDetails generateToken(User user) throws AuthenticationException;
    protected abstract Authentication decodeToken(String token) throws AuthenticationException;
}
