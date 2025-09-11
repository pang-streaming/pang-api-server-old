package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    Page<PostEntity> findPostsByCommunity(Long communityId, Pageable pageable);
}
