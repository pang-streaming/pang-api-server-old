package com.pangapiserver.domain.stream.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.repository.StreamRepository;

@Service
@RequiredArgsConstructor
public class StreamService {
    private final StreamRepository repository;

    public List<StreamEntity> getAll() {
        return repository.findAllByOrderByIdDesc();
    }
}
