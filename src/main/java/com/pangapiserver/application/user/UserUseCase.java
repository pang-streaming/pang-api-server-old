package com.pangapiserver.application.user;

import com.pangapiserver.application.user.data.UpdateInfoRequest;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUseCase {
    private final UserAuthenticationHolder holder;
    private final UserService service;

    public void updateInfo(UpdateInfoRequest request) {
        UserEntity user = holder.current();
        user.updateInfo(request.nickname(), request.age(), request.gender(), request.profileImage(), request.bannerImage());
        service.update(user);
    }
}
