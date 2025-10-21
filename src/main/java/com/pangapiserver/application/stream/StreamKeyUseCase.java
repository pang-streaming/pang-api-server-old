package com.pangapiserver.application.stream;

import com.pangapiserver.application.stream.data.response.StreamKeyResponse;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.service.CommunityService;
import com.pangapiserver.domain.stream.service.StreamKeyService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.enumeration.Role;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class StreamKeyUseCase {
    private final StreamKeyService service;
    private final CommunityService communityService;
    private final UserService userService;
    private final UserAuthenticationHolder holder;

    public DataResponse<StreamKeyResponse> createKey() {
        UserEntity user = holder.current();
        if (user.getRole() == Role.USER) {
            CommunityEntity community = new CommunityEntity(user, user.getNickname() + "님의 커뮤니티에 오신것을 환영합니다.");
            user.changeRoleToStreamer(community);
            communityService.save(community);
            userService.update(user);
        }
        return DataResponse.created("스트리밍 키 생성 성공", StreamKeyResponse.of(service.create(user)));
    }

    public DataResponse<StreamKeyResponse> getKey() {
        UserEntity user = holder.current();
        return DataResponse.ok("스트리밍 키 조회 성공", StreamKeyResponse.of(service.getByUser(user)));
    }
}
