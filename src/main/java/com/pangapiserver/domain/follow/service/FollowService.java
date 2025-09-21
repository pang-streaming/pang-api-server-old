package com.pangapiserver.domain.follow.service;

import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.application.follow.mapper.FollowConverter;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.exception.UserNotFoundException;
import com.pangapiserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "followings", key = "#username")
    public List<FollowingResponse> getFollowingsByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<FollowEntity> followings = followRepository.findFollowingByUser(user);
        return followConverter.mapToFollowingResponse(
                followings,
                FollowEntity::getFollower,
                FollowingResponse::of
        );
    }

    public List<FollowEntity> getFollowingEntitiesByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        return followRepository.findFollowingByUser(user);
    }

    @Cacheable(value = "followers", key = "#username")
    public List<FollowingResponse> getFollowersByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
        List<FollowEntity> followers = followRepository.findFollowerByUser(user);
        return followConverter.mapToFollowingResponse(
                followers,
                FollowEntity::getUser,
                FollowingResponse::of
        );
    }

    public void followOrNot(UserEntity following, String username) {
        UserEntity follower = userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
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

    public boolean isFollowing(UUID followingId, UUID followerId) {
        UserEntity following = userRepository.findById(followingId).orElseThrow(UserNotFoundException::new);
        UserEntity follower = userRepository.findById(followerId).orElseThrow(UserNotFoundException::new);
        return followRepository.findByUserAndFollower(following, follower).isPresent();
    }
}