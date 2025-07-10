package com.pangapiserver.domain.follow.repository;

import java.util.List;
import java.util.UUID;

import com.pangapiserver.application.follow.data.FollowerCountResponse;
import com.querydsl.core.Tuple;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import com.pangapiserver.domain.follow.entity.QFollowEntity;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<FollowerCountResponse> countByFollowerIds(List<UUID> ids) {
        QFollowEntity f = QFollowEntity.followEntity;
        List<Tuple> tuples = jpaQueryFactory
                .select(f.follower.id, f.count())
                .from(f)
                .where(f.follower.id.in(ids))
                .groupBy(f.follower.id)
                .fetch();

        return tuples.stream()
                .map(tuple -> new FollowerCountResponse(tuple.get(f.follower.id), tuple.get(1, Long.class)))
                .collect(Collectors.toList());
    }
}
