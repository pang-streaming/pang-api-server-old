package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.request.UpdateStreamRequest;
import com.pangapiserver.application.stream.data.response.StreamInfoResponse;
import com.pangapiserver.application.stream.data.response.StreamResponse;
import com.pangapiserver.application.stream.data.response.StreamUserResponse;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.service.FollowService;
import com.pangapiserver.domain.interest.repository.InterestRepository;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.redis.service.RedisService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import com.pangapiserver.infrastructure.stream.properties.StreamProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class StreamUseCase {
    private final StreamService service;
    private final StreamKeyService keyService;
    private final FollowService followService;
    private final UserAuthenticationHolder holder;
    private final StreamProperties properties;
    private final InterestRepository interestRepository;
    private final RedisService redisService;

    public DataResponse<StreamInfoResponse> getStreamById(UUID streamId) {
        StreamEntity stream = service.getByStreamId(streamId, holder.current());
        int followers = followService.getFollowersByUsername(stream.getUser().getUsername()).size();
        boolean isFollowed = followService.isFollowing(holder.current(), stream.getUser());
        int viewCount = redisService.getViewCount(stream.getUser().getUsername());
        return DataResponse.ok("스트리밍 정보 조회 성공", StreamInfoResponse.of(stream, followers, isFollowed, viewCount));
    }

    public DataResponse<List<StreamResponse>> getLiveStreams() {
        return DataResponse.ok("생방송중 목록 조회 성공", service.getLiveStreams().stream().map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername()))).toList());
    }

    public DataResponse<List<StreamResponse>> getStreamsByCategory(Long categoryId) {
        return DataResponse.ok("카테고리별 스트림 조회 성공", service.getStreamsByCategory(categoryId).stream().map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername()))).toList());
    }

    public DataResponse<List<StreamResponse>> getFollowingLiveStreams() {
        List<StreamResponse> streams = service.getLiveStreams().stream().map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername()))).toList();
        List<FollowEntity> follows = followService.getFollowingEntitiesByUsername(holder.current().getUsername());

        Set<String> followingUsernames = follows.stream()
                .map(f -> f.getFollower().getUsername())
                .collect(Collectors.toSet());

        List<StreamResponse> response = streams.stream()
                .filter(stream -> followingUsernames.contains(stream.username()))
                .toList();
        return DataResponse.ok("생방송중 목록 조회 성공", response);
    }

    /** 스트림 키로 스트림 생성 */
    public DataResponse<StreamUserResponse> createStreamByKey(String key) {
        StreamKeyEntity byStreamKey = keyService.getByStreamKey(key);
        UserEntity streamer = byStreamKey.getUser();
        service.getLiveStreamByUserOrNull(streamer).ifPresent(service::closeStream);
        StreamEntity stream = StreamEntity.builder()
                .user(streamer)
                .title(streamer.getNickname() + "님의 방송")
                .url(properties.getUrl() + streamer.getUsername() + "/" + byStreamKey.getCreatedAt() + "/playlist.m3u8")
                .thumbnail(properties.getUrl() + streamer.getUsername() + "/" + byStreamKey.getCreatedAt() + "/thumbnail.jpg")
                .build();
        service.save(stream);
        service.saveDocument(stream);
        return DataResponse.ok("스트림 생성 성공", StreamUserResponse.of(byStreamKey));
    }

    public DataResponse<StreamInfoResponse> updateStream(String key, UpdateStreamRequest request) {
        StreamKeyEntity byStreamKey = keyService.getByStreamKey(key);
        StreamEntity stream = service.getLiveStreamByUser(byStreamKey.getUser());
        StreamEntity updateStream = service.updateStream(stream, holder.current(), request.title(), request.categoryId(), request.tags(), request.thumbnail());
        int followers = followService.getFollowersByUsername(updateStream.getUser().getUsername()).size();
        int viewCount = redisService.getViewCount(updateStream.getUser().getUsername());
        return DataResponse.ok("스트리밍 정보 수정 성공", StreamInfoResponse.of(updateStream, followers, false, viewCount));
    }

    public DataResponse<Page<StreamResponse>> search(String keyword, Pageable pageable) {
        UserEntity user = holder.current();
        List<String> chips = interestRepository.getChipsWithUser(user);
        return DataResponse.ok("방송 검색 성공", service.searchByTitle(keyword, chips, pageable));
    }

    public Response closeStream(String key) {
        StreamKeyEntity byStreamKey = keyService.getByStreamKey(key);
        StreamEntity stream = service.getLiveStreamByUser(byStreamKey.getUser());
        service.closeStream(stream);
        return Response.ok("스트리밍 종료 성공");
    }

    public DataResponse<List<StreamResponse>> getEndedStreamsOfFollowings() {
        UserEntity currentUser = holder.current();
        List<StreamEntity> endedStreams = service.getEndedStreamsOfFollowings(currentUser);
        List<StreamResponse> response = endedStreams.stream()
                .map(s -> StreamResponse.of(s, redisService.getViewCount(s.getUser().getUsername())))
                .toList();
        return DataResponse.ok("팔로우하는 유저의 종료된 방송 목록 조회 성공", response);
    }
}
