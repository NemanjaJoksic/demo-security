/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.crypto;

/**
 *
 * @author joksin
 */
public interface PasswordEncoder {

    public String encode(String rawPassword);
    public boolean matches(String rawPassword, String encodedPassword);
    
}
