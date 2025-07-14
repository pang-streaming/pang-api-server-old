package com.pangapiserver.domain.follow.service;

import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.application.follow.mapper.FollowConverter;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final FollowConverter followConverter;

    public List<FollowingResponse> getByFollowing(UUID id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        List<FollowEntity> followings = followRepository.findByUser(user);
        return followConverter.mapToFollowingResponse(
                followings,
                FollowEntity::getFollower,
                FollowingResponse::toFollowing
        );
    }

    public List<FollowingResponse> getByFollower(UUID id) {
        UserEntity user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        List<FollowEntity> followers = followRepository.findByFollower(user);
        return followConverter.mapToFollowingResponse(
                followers,
                FollowEntity::getUser,
                FollowingResponse::toFollower
        );
    }

    public void followOrNot(UserEntity following, UUID id) {
        UserEntity follower = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
        Optional<FollowEntity> follow = followRepository.findByUserAndFollower(following, follower);
        if (follow.isPresent()) {
            followRepository.delete(follow.get());
        } else {
            followRepository.save(FollowEntity.builder()
                    .user(following)
                    .follower(follower)
                    .build());
        }
    }
}