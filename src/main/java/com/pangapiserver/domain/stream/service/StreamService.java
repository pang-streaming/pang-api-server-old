package com.pangapiserver.domain.stream.service;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.exception.StreamKeyNotFoundException;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;
    private final UserAuthenticationHolder holder;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    public StreamEntity getByStreamingId(UserEntity user) {
        return repository.findByUserAndEndAtNull(user)
            .orElseThrow(StreamKeyNotFoundException::new);
    }

    public void save(StreamEntity stream) {
        repository.save(stream);
    }

    public void update(StreamEntity stream) {
        stream.updateEndAt();
        repository.save(stream);
    }
}
