package com.pangapiserver.application.temp;

import com.pangapiserver.application.temp.data.request.UpdateTempStreamRequest;
import com.pangapiserver.domain.stream.entity.TempStreamEntity;
import com.pangapiserver.domain.stream.service.TempStreamService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.cloudflare.data.StartStreamResponse;
import com.pangapiserver.infrastructure.cloudflare.service.CloudflareService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
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
    private final TempStreamService tempStreamService;

    public DataResponse<StartStreamResponse> createLive() {
        UserEntity user = holder.current();
        StartStreamResponse liveInput = tempStreamService.findByUser(user)
                .map(stream -> service.getLiveInput(stream.getUid()).block())
                .orElseGet(() -> {
                    StartStreamResponse response = service.createLiveInput(user).block();
                    if (response != null && response.getSuccess() && response.getResult() != null) {
                        StartStreamResponse.Result result = response.getResult();
                        TempStreamEntity newStream = TempStreamEntity.builder()
                                .user(user)
                                .uid(result.getUid())
                                .webRtcUrl(result.getWebRTC() != null ? result.getWebRTC().getUrl() : null)
                                .webRtcPlaybackUrl(result.getWebRTCPlayback() != null ? result.getWebRTCPlayback().getUrl() : null)
                                .streamName(result.getMeta() != null ? result.getMeta().getName() : null)
                                .build();
                        tempStreamService.save(newStream);
                    }
                    return response;
                });
        return DataResponse.ok("스트림 생성 성공", liveInput);
    }

    public DataResponse<TempStreamEntity> getTempStreamByUid(String uid) {
        return DataResponse.ok("스트림 조회 성공", tempStreamService.findByUid(uid));
    }

    public Response updateTempStream(String uid, UpdateTempStreamRequest request) {
        TempStreamEntity updatedStream = tempStreamService.updateStreamInfo(
                uid,
                request.streamName(),
                request.isLive()
        );
        return Response.ok("스트림 정보 수정 성공");
    }
}
