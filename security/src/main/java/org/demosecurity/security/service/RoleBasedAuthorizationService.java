/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demosecurity.security.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.demosecurity.security.exception.AccessForbiddenException;
import org.demosecurity.security.exception.AuthenticationException;
import org.demosecurity.security.model.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author joksin
 */
@Service
@ConditionalOnProperty(name = "security.authorization.type", havingValue = "roleBased")
public class RoleBasedAuthorizationService implements AuthorizationService, InitializingBean {
    
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Authorization {
        
        Pattern pattern;
        String path;
        String method;
        List<String> roles = new LinkedList<>();

        public Authorization() {}
        
        public Authorization(Pattern pattern, String method, List<String> roles) {
            this.pattern = pattern;
            this.method = method;
            this.roles = roles;
        }
        
    }
    
    private static final Logger logger = LoggerFactory.getLogger(RoleBasedAuthorizationService.class);
    
    private final List<Authorization> authorizations = new LinkedList<>();
    
    @Override
    public Authentication authorize(Authentication authentication) throws AuthenticationException {
        String url = authentication.getPath();
        String method = authentication.getMethod();
        for(Authorization authorization : authorizations) {
            Matcher matcher = authorization.pattern.matcher(url);
            if(matcher.matches() && method.equals(authorization.method)) {
                if(!CollectionUtils.containsAny(authentication.getRoles(), authorization.roles))
                    throw new AccessForbiddenException();
            }
        }
        return new Authentication(authentication.getPrincipal());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("RoleBasedAuthorizationService is configured");
        
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("auth.json");
        if (url == null) {
            logger.info("auth.json file not found on classpath. Authorization is disabled");
            return;
        }

        logger.info("Loading authorization setup from auth.json");

        InputStream is = classLoader.getResourceAsStream("auth.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Authorization> authList = mapper.readValue(is, new TypeReference<List<Authorization>>() {});

        if (authList.isEmpty()) {
            logger.info("auth.json file is empty or missing. No specific authorization restrictions are set");
            return;
        }
        
        for(Authorization authorization : authList) {
            Pattern pattern = Pattern.compile(authorization.path.replaceAll("\\*\\*", "\\.\\*"));
            authorization.pattern = pattern;
            authorizations.add(authorization);
        }
        
    }
    
}
