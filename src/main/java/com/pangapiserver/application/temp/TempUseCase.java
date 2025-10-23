package com.pangapiserver.application.temp;

import com.pangapiserver.application.temp.data.CreateLiveResponse;
import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.exception.StreamAlreadyEndedException;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.cloudflare.service.CloudflareService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.encode.AESEncoder;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class TempUseCase {
    private final CloudflareService service;
    private final UserAuthenticationHolder holder;
    private final StreamKeyService streamKeyService;
    private final AESEncoder aesEncoder;

    public DataResponse<CreateLiveResponse> createLive() {
        UserEntity user = holder.current();
        log.warn("111111");
        String key = "";
        String webRtcUrl = "";
        StreamKeyEntity streamKeyEntity = streamKeyService.getKeyByUser(user);
        if (streamKeyEntity == null) {
            StartStreamResponse streamResponse = service.createLiveInput(user).block();
            assert streamResponse != null;
            if (streamResponse.getResult() == null) {
                throw new StreamAlreadyEndedException();
            }
            key = streamKeyService.createTemp(user, streamResponse.getResult().getWebRTC().getUrl(), streamResponse.getResult().getUid());
            webRtcUrl = streamResponse.getResult().getWebRTC().getUrl();
        } else {
            key = aesEncoder.decrypt(streamKeyEntity.getKey());
            webRtcUrl = streamKeyEntity.getKey();
        }

        return DataResponse.ok("스트림 생성 성공", new CreateLiveResponse(key, webRtcUrl));
    }
}
