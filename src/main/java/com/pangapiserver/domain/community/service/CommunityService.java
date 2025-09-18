package com.pangapiserver.domain.community.service;

import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.exception.CommunityNotfoundException;
import com.pangapiserver.domain.community.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository repository;

    public CommunityEntity findById(Long communityId) {
           return repository.findById(communityId)
                   .orElseThrow(CommunityNotfoundException::new);
    }
}
