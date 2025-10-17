package com.pangapiserver.domain.interest.repository;

import com.pangapiserver.domain.interest.entity.QInterestEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InterestCustomRepositoryImpl implements InterestCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> getChipsWithUser(UserEntity user) {
        QInterestEntity qInterestEntity = QInterestEntity.interestEntity;

        return queryFactory
                .select(qInterestEntity.chip.stringValue())
                .from(qInterestEntity)
                .where(qInterestEntity.user.id.eq(user.getId()))
                .fetch();
    }
}
