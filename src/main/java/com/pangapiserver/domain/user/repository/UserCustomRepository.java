package com.pangapiserver.domain.user.repository;

import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.Optional;

public interface UserCustomRepository {
    Optional<UserEntity> findUserWithInterestsByUsername(String username);
}
