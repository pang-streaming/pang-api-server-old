package com.pangapiserver.domain.interest.repository;

import com.pangapiserver.domain.user.entity.UserEntity;

import java.util.List;

public interface InterestCustomRepository {
    List<String> getChipsWithUser(UserEntity user);
}
