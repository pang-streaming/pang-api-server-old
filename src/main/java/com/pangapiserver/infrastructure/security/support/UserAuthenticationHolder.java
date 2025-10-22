package com.pangapiserver.infrastructure.security.support;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.entity.UserPrincipal;
import com.pangapiserver.domain.user.exception.UnauthorizedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationHolder {
    public UserEntity current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new UnauthorizedException();
        }
        
        Object principal = authentication.getPrincipal();
        if (!(principal instanceof UserPrincipal)) {
            throw new UnauthorizedException();
        }
        
        return ((UserPrincipal) principal).user();
    }
}
