package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.response.StreamInfoResponse;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.domain.follow.service.FollowService;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class StreamUseCase {
    private final StreamService service;
    private final FollowService followService;

    public DataResponse<StreamInfoResponse> getStreamById(UUID streamId) {
        StreamEntity stream = service.getByStreamId(streamId);
        int followers = followService.getFollowersByUsername(stream.getUser().getUsername()).size();
        return DataResponse.ok("스트리밍 정보 조회 성공", StreamInfoResponse.of(stream, followers));
    }

    public DataResponse<List<StreamResponse>> getLiveStreams() {
        return DataResponse.ok("생방송중 목록 조회 성공", service.getLiveStreams().stream().map(StreamResponse::of).toList());
    }
}
