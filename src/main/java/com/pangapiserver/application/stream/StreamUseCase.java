package com.pangapiserver.application.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.application.stream.data.StreamListResponse;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StreamUseCase {
    private final StreamService service;

    public List<StreamListResponse> getItems() {
        List<StreamEntity> items = service.getAll();
        return toResponse(items);
    }

    public List<StreamListResponse> getPopularItems() {
        List<StreamEntity> items = service.getAll();
        return toResponse(items);
    }

    private List<StreamListResponse> toResponse(List<StreamEntity> items) {
        return items.stream()
                .map(StreamListResponse::of)
                .collect(Collectors.toList());
    }
}
