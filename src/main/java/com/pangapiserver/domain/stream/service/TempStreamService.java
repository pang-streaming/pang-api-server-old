package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.TempStreamEntity;
import com.pangapiserver.domain.stream.exception.StreamNotFoundException;
import com.pangapiserver.domain.stream.repository.TempStreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TempStreamService {
    private final TempStreamRepository repository;

    public Optional<TempStreamEntity> findByUser(UserEntity user) {
        return repository.findByUser(user);
    }

    public TempStreamEntity save(TempStreamEntity tempStream) {
        return repository.save(tempStream);
    }

    public TempStreamEntity findByUid(String uid) {
        return repository.findByUid(uid).orElseThrow(StreamNotFoundException::new);
    }
}
