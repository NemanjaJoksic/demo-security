/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import org.demosecurity.security.crypto.PasswordEncoder;
import org.demosecurity.security.dao.UserDao;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.exception.UserNotExistException;
import org.demosecurity.security.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joksin
 */
@Service
public class RdbUserService implements UserService, InitializingBean {

    public static final Logger logger = LoggerFactory.getLogger(RdbUserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User loadUserByUsername(String username) throws AuthenticationException {
        User user = userDao.findByUsername(username);

        if (user == null)
            throw new UserNotExistException(username);

        return user;
    }

    @Override
    public User createUser(User user, String creationIdAddress) {
        user.setCreationIpAddress(creationIdAddress);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Relational database is configured for user store");
    }

}
