package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.exception.CommunityNotfoundException;
import com.pangapiserver.domain.community.repository.CommunityRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository repository;

    public CommunityEntity save(CommunityEntity entity) {
        return repository.save(entity);
    }

    public CommunityEntity findById(Long communityId) {
        return repository.findById(communityId)
                .orElseThrow(CommunityNotfoundException::new);
    }

    public CommunityEntity findByUser(UserEntity user) {
        return repository.findByUser(user).orElseThrow(CommunityNotfoundException::new);
    }

    public Optional<CommunityEntity> findByUserOrNull(UserEntity user) {
        return repository.findByUser(user);
    }
}
