package com.pangapiserver.application.video;

import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.domain.video.service.VideoService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.redis.service.RedisService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VideoUseCase {
    private final VideoService videoService;
    private final StreamService streamService;
    private final UserAuthenticationHolder holder;
    private final UserService userService;
    private final RedisService redisService;

    public DataResponse<List<StreamResponse>> getRecent() {
        List<StreamEntity> entities = videoService.getRecent(holder.current());
        List<StreamResponse> data = entities.stream()
                .map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername())))
                .toList();
        return DataResponse.ok("최근 시청한 동영상 조회 성공", data);
    }

    public DataResponse<StreamResponse> getLiveVideoByUsername(String username) {
        UserEntity streamer = userService.getByUsername(username);
        return DataResponse.ok("유저의 라이브 영상 조회 성공", StreamResponse.of(streamService.getLiveStreamByUser(streamer), redisService.getViewCount(streamer.getUsername())));
    }

    public DataResponse<List<StreamResponse>> getRecordedVideoByUsername(String username) {
        UserEntity streamer = userService.getByUsername(username);
        List<StreamResponse> data = streamService.getRecordedStreamByUser(streamer).stream()
                .map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername()))).toList();
        return DataResponse.ok("유저의 동영상 조회 성공", data);
    }
}
