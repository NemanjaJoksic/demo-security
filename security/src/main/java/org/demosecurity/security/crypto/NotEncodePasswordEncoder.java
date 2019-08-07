/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.crypto;

import org.springframework.stereotype.Component;

/**
 *
 * @author joksin
 */
@Component
public class NotEncodePasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String rawPassword) {
        return rawPassword;
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }
    
}
