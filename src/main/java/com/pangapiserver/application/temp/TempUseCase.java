package com.pangapiserver.application.temp;

import com.pangapiserver.domain.stream.exception.StreamAlreadyEndedException;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.cloudflare.service.CloudflareService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class TempUseCase {
    private final CloudflareService service;
    private final UserAuthenticationHolder holder;
    private final StreamKeyService streamKeyService;

    public DataResponse<String> createLive() {
        UserEntity user = holder.current();
        String key = "";
        if (streamKeyService.getByUser(user) == null) {
            StartStreamResponse streamResponse = service.createLiveInput(user).block();
            assert streamResponse != null;
            if (streamResponse.getResult() == null) {
                throw new StreamAlreadyEndedException();
            }
            key = streamKeyService.createTemp(user, streamResponse.getResult().getWebRTC().getUrl(), streamResponse.getResult().getUid());
        }
        return DataResponse.ok("스트림 생성 성공", key);
    }
}
