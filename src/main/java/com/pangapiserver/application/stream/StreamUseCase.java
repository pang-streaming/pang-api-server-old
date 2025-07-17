package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.StreamListResponse;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.infrastructure.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class StreamUseCase {
    private final StreamService service;

    public List<StreamListResponse> getItems() {
        List<StreamEntity> items = service.getAll();
        return StreamListResponse.of(items);
    }

    public List<StreamListResponse> getPopularItems() {
        List<StreamEntity> items = service.getAll();
        return StreamListResponse.of(items);
    }
}
