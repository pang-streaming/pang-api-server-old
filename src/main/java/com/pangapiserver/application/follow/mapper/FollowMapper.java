package com.pangapiserver.application.follow.mapper;

import java.util.*;
import java.util.function.Function;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.application.follow.data.FollowingResponse;
import com.pangapiserver.domain.follow.repository.FollowRepository;

@Service
public class FollowMapper {

    private final FollowRepository followRepository;

    public FollowMapper(FollowRepository followRepository) {
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

        List<Object[]> countList = followRepository.countByFollowerIds(targetIds);

        Map<UUID, Long> countMap = new HashMap<>();
        for (Object[] row : countList) {
            UUID id = (UUID) row[0];
            Long count = (Long) row[1];
            countMap.put(id, count);
        }

        return followEntities.stream()
                .map(follow -> {
                    UUID id = countTargetExtractor.apply(follow).getId();
                    long count = countMap.getOrDefault(id, 0L);
                    return responseMapper.apply(countTargetExtractor.apply(follow), count);
                })
                .toList();
    }
}
