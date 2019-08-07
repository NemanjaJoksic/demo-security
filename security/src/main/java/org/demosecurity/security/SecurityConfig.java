/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author joksin
 */
@Configuration
@Getter
public class SecurityConfig {
    
    @Value("${security.jwt.token.secret.key:123}")
    private String jwtTokenSecretKey;
    
    @Value("${security.jwt.token.expiration.time:3600}")
    private Integer jwtTokenExpirationTime;
    
}
