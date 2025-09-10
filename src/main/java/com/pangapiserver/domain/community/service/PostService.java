package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public PostEntity save(PostEntity entity) {
        return repository.save(entity);
    }
}
