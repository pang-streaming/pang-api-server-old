package com.pangapiserver.domain.follow.repository;

import com.pangapiserver.domain.follow.entity.FollowEntity;
import com.pangapiserver.domain.follow.entity.QFollowEntity;
import com.pangapiserver.domain.user.entity.QUserEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Map<UUID, Long> countByFollowerIds(List<UUID> ids) {
        QFollowEntity f = QFollowEntity.followEntity;
        List<Tuple> tuples = jpaQueryFactory
                .select(f.follower.id, f.count())
                .from(f)
                .where(f.follower.id.in(ids))
                .groupBy(f.follower.id)
                .fetch();

        return tuples.stream()
            .collect(Collectors.toMap(
                tuple -> tuple.get(f.follower.id),
                tuple -> tuple.get(1, Long.class)
            ));
    }

    @Override
    public List<FollowEntity> findFollowingsWithFollowerByUser(UserEntity user) {
        QFollowEntity followEntity = QFollowEntity.followEntity;
        QUserEntity userEntity = new QUserEntity("userEntity");

        return jpaQueryFactory
                .selectFrom(followEntity)
                .join(followEntity.follower, userEntity).fetchJoin()
                .where(followEntity.user.eq(user))
                .fetch();
    }
}
