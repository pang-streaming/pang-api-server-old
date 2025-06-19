package com.pangapiserver.domain.stream.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.repository.StreamRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;
    private final UserAuthenticationHolder holder;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }

    //test
    @Transactional
    public void save(String title) {
        repository.save(StreamEntity.builder()
            .user(holder.current())
            .title(title)
            .url("")
            .startedAt(LocalDateTime.now())
            .endAt(LocalDateTime.now())
            .build());
    }
}
