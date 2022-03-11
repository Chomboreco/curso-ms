package com.chombo.ms.springbootserviciooauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        if (authentication.getDetails() instanceof WebAuthenticationDetails) {
            return;
        }

        /*
         * if (authentication.getName().equalsIgnoreCase("frontendapp")) {
         * return;
         * }
         */

        UserDetails user = (UserDetails) authentication.getPrincipal();
        log.info("Success Login: " + user.getUsername());
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.error("Error Login: " + exception.getMessage());
    }
}
