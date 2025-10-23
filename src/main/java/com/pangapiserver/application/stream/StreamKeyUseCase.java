package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.application.stream.data.response.StreamStatusResponse;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.stream.entity.StreamKeyEntity;
import com.pangapiserver.domain.stream.entity.StreamType;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.stream.service.StreamService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.enumeration.Role;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class StreamKeyUseCase {
    private final StreamKeyService service;
    private final StreamService streamService;
    private final CommunityService communityService;
    private final UserService userService;
    private final UserAuthenticationHolder holder;

    public DataResponse<StreamKeyResponse> createKey(StreamType type) {
        UserEntity user = holder.current();
        if (user.getRole() == Role.USER) {
            CommunityEntity community = new CommunityEntity(user, user.getNickname() + "님의 커뮤니티에 오신것을 환영합니다.");
            user.changeRoleToStreamer(community);
            communityService.save(community);
            userService.update(user);
        }
        return DataResponse.created("스트리밍 키 생성 성공", StreamKeyResponse.of(service.create(user, type)));
    }

    public DataResponse<StreamKeyResponse> getKey() {
        UserEntity user = holder.current();
        return DataResponse.ok("스트리밍 키 조회 성공", StreamKeyResponse.of(service.getByUser(user)));
    }

    public DataResponse<StreamStatusResponse> isStreaming() {
        UserEntity user = holder.current();
        log.warn("createKey11111: ");
        String key = service.getByUser(user);
        log.warn("createKey22222: ");
        StreamKeyEntity byStreamKey = service.getByStreamKey(key);
        log.warn("createKe33333: ");
        StreamEntity stream = streamService.getLiveStreamByUser(user);
        log.warn("createKey44444: ");
        Long categoryId = stream.getCategory() != null ? stream.getCategory().getId() : null;
        return DataResponse.ok("스트리밍 상태 확인 성공", StreamStatusResponse.of(stream.getTitle(), categoryId, stream.getStatus(), byStreamKey.getType()));
    }
}
