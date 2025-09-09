package com.pangapiserver.application.follow.mapper;

import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FollowConverter {

    private final FollowRepository followRepository;

    public List<FollowingResponse> mapToFollowingResponse(
        List<FollowEntity> followEntities,
        Function<FollowEntity, UserEntity> countTargetExtractor,
        BiFunction<UserEntity, Long, FollowingResponse> responseMapper
    ) {
        List<UUID> targetIds = followEntities.stream()
            .map(countTargetExtractor)
            .map(UserEntity::getId)
            .distinct()
            .toList();

        Map<UUID, Long> countMap = followRepository.countByFollowerIds(targetIds).entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return followEntities.stream()
            .map(follow -> {
                UserEntity target = countTargetExtractor.apply(follow);
                long count = countMap.getOrDefault(target.getId(), 0L);
                return responseMapper.apply(target, count);
            })
            .toList();
    }
}

