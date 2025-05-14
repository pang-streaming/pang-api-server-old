package com.pangapiserver.infrastructure.security.support;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.entity.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationHolder {
    public UserEntity current() {
        return ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).user();
    }
}
