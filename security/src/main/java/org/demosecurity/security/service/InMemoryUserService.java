/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import java.util.Arrays;
import org.demosecurity.security.crypto.PasswordEncoder;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.model.Role;
import org.demosecurity.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 *
 * @author joksin
 */
@Service
@ConditionalOnProperty(name = "security.user.store.type", havingValue = "in-memory")
public class InMemoryUserService implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User loadUserByUsername(String username) throws AuthenticationException {
        return User.builder()
                .username("nemanja")
                .password(passwordEncoder.encode("123"))
                .roles(Arrays.asList(new Role(1, "USER_ROLE")))
                .build();
    }

    @Override
    public User createUser(User user, String creationIdAddress) throws AuthenticationException {
        return null;
    }
    
}
