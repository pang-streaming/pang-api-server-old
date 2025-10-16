package com.pangapiserver.application.user;

import com.pangapiserver.application.follow.data.FollowerCountResponse;
import com.pangapiserver.application.user.data.UpdateInfoRequest;
import com.pangapiserver.application.user.data.UserInfoResponse;
import com.pangapiserver.application.user.data.UserListResponse;
import com.pangapiserver.application.user.data.UserDetailResponse;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.community.entity.CommunityEntity;
import com.pangapiserver.domain.community.repository.CommunityRepository;
import com.pangapiserver.domain.follow.repository.FollowCustomRepository;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class UserUseCase {
    private final UserAuthenticationHolder holder;
    private final UserService service;
    private final CashService cashService;
    private final CommunityRepository communityRepository;
    private final FollowCustomRepository followCustomRepository;
    private final FollowRepository followRepository;

    public DataResponse<UserInfoResponse> getMyInfo() {
        UserEntity user = holder.current();
        return DataResponse.ok("내 정보 조회 성공", UserInfoResponse.of(user, cashService.getBalance(user)));
    }

    public void updateInfo(UpdateInfoRequest request) {
        UserEntity user = holder.current();
        user.updateInfo(request.nickname(), request.age(), request.gender(), request.profileImage(), request.bannerImage(),
            request.description());
        service.update(user);
    }

    public DataResponse<List<UserListResponse>> getUsers() {
        UserEntity me = holder.current();
        List<UserEntity> users = service.getUsers(me.getId());
        List<FollowerCountResponse> followers = service.getFollowers(me.getId());
        return DataResponse.ok("유저 목록 조회 성공", UserListResponse.of(users, followers));
    }

    public Response deleteUser() {
        UserEntity user = holder.current();
        service.deleteByUser(user);
        return Response.ok("회원 탈퇴 성공");
    }

    public DataResponse<UserDetailResponse> getUserDetailByUsername(String username) {
        UserEntity user = service.getByUsername(username);
        UserEntity currentUser = holder.current();
        Optional<CommunityEntity> community = communityRepository.findByUser(user);
        Long communityId = community.map(CommunityEntity::getId).orElse(null);
        Map<UUID, Long> followerCount = followCustomRepository.countByFollowerIds(List.of(user.getId()));
        boolean isFollowed = followRepository.findByUserAndFollower(currentUser, user).isPresent();
        return DataResponse.ok("유저 정보 조회 성공", UserDetailResponse.of(user, communityId, followerCount.getOrDefault(user.getId(), 0L), isFollowed));
    }
}
