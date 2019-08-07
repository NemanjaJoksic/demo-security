/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.exception.ExpiredJwtTokenException;
import org.demosecurity.security.exception.InvalidJwtTokenException;
import org.demosecurity.security.model.Authentication;
import org.demosecurity.security.model.TokenDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author joksin
 */
@Service
@ConditionalOnProperty(name = "security.authentication.type", havingValue = "jwt")
public class JwtTokenAuthenticationService extends TokenAuthenticationService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenAuthenticationService.class);
    
    @Value("${security.authentication.jwt.secret-key}")
    private String secretKey;
    
    @Value("${security.authentication.jwt.expiration-time}")
    private Integer expirationTime;
    
    @Override
    protected TokenDetails generateToken(String username) {
        Claims claims = Jwts.claims();
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

        return new TokenDetails(
                username,
                expirationTime,
                TokenDetails.BEARER,
                accessToken
        );
    }

    @Override
    protected Authentication decodeToken(String token) throws AuthenticationException {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Authentication authentication = new Authentication(jwsClaims.getBody().getSubject());
            return authentication;
        } catch (ExpiredJwtException ex) {
            throw new ExpiredJwtTokenException();
        } catch (Exception ex) {
            throw new InvalidJwtTokenException();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("JwtTokenAuthenticationService is configured");
    }
    
}
