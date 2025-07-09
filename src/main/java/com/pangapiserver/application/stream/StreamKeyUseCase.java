package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StreamKeyUseCase {
    private final StreamKeyService service;
    private final UserAuthenticationHolder holder;

    public DataResponse<StreamKeyResponse> createKey() {
        UserEntity user = holder.current();
        return DataResponse.created("스트리밍 키 생성 성공", StreamKeyResponse.of(service.create(user)));
    }

    public DataResponse<StreamKeyResponse> getKey() {
        UserEntity user = holder.current();
        return DataResponse.ok("스트리밍 키 조회 성공", StreamKeyResponse.of(service.getByUser(user)));
    }
}
