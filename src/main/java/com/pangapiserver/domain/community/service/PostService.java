package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.community.exception.PostNotFoundException;
import com.pangapiserver.domain.community.repository.PostCustomRepository;
import com.pangapiserver.domain.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;
    private final PostCustomRepository customRepository;

    public PostEntity save(PostEntity entity) {
        return repository.save(entity);
    }

    public Page<PostEntity> getPostsByCommunity(UserEntity user, Long communityId, Pageable pageable, PostFilterType filter) {
        return customRepository.findPostsByCommunity(user, communityId, pageable, filter);
    }

    public PostEntity findById(Long id) {
        return repository.findById(id).orElseThrow(PostNotFoundException::new);
    }
}
