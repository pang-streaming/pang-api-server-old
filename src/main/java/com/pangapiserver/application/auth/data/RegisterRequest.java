package com.pangapiserver.application.auth.data;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.enumeration.Role;

import java.time.LocalDate;

public record RegisterRequest(
    String email,
    String id,
    String password
) {
    public UserEntity toEntity(String encodedPassword) {
        return UserEntity.builder()
            .email(email)
            .username(id)
            .nickname(id)
            .age(LocalDate.now())
            .password(encodedPassword)
            .role(Role.USER)
            .isAlarm(false)
            .isAdult(false)
            .build();
    }
}