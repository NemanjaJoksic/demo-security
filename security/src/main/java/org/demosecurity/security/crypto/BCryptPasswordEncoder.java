/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.crypto;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 *
 * @author joksin
 */
@Component
@ConditionalOnProperty(name = "security.password.encoding.type", havingValue = "bcrypt")
public class BCryptPasswordEncoder implements PasswordEncoder, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(NotEncodePasswordEncoder.class);

    @Override
    public String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("BCryptPasswordEncoder is configured");
    }
    
}
