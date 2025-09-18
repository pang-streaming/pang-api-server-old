package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<PostEntity> findPostsByCommunity(UserEntity user, Long communityId, Pageable pageable, PostFilterType filter);
}
