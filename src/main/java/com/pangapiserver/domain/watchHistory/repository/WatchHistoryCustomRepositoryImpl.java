package com.pangapiserver.domain.watchHistory.repository;

import com.pangapiserver.domain.stream.entity.StreamEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.watchHistory.entity.QWatchHistoryEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WatchHistoryCustomRepositoryImpl implements WatchHistoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<StreamEntity> getRecent(UserEntity user) {
        QWatchHistoryEntity qWatchHistory = QWatchHistoryEntity.watchHistoryEntity;
        return jpaQueryFactory
                .select(qWatchHistory.stream)
                .from(qWatchHistory)
                .where(qWatchHistory.user.eq(user))
                .orderBy(qWatchHistory.createdAt.desc())
                .limit(10)
                .fetch();
    }
}
