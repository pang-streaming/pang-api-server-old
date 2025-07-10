package com.pangapiserver.application.follow.mapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.BiFunction;
import org.springframework.stereotype.Component;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.domain.follow.repository.FollowRepository;
import com.pangapiserver.application.follow.data.FollowerCountResponse;

@Component
public class FollowConverter {

    private final FollowRepository followRepository;

    public FollowConverter(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

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

        List<FollowerCountResponse> countList = followRepository.countByFollowerIds(targetIds).entrySet().stream()
            .map(set -> new FollowerCountResponse(set.getKey(), set.getValue()))
            .toList();

        Map<UUID, Long> countMap = new HashMap<>();
        countList.forEach(count ->
            countMap.put(count.id(), count.count())
        );
//        for (FollowerCountResponse row : countList) {
//            UUID id = row.id();
//            Long count = row.count();
//            countMap.put(id, count);
//        }

        return followEntities.stream()
                .map(follow -> {
                    UUID id = countTargetExtractor.apply(follow).getId();
                    long count = countMap.getOrDefault(id, 0L);
                    return responseMapper.apply(countTargetExtractor.apply(follow), count);
                })
                .toList();
    }
}
