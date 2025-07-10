package com.pangapiserver.domain.cash.repository;

import com.pangapiserver.domain.cash.data.CashTransactionDto;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pangapiserver.domain.cash.entity.QCashEntity.cashEntity;

@RequiredArgsConstructor
public class CashQueryRepositoryImpl implements CashQueryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Integer> sumAmountByUserId(UUID userId) {
        Integer sum = queryFactory
            .select(cashEntity.amount.sum())
            .from(cashEntity)
            .where(cashEntity.user.id.eq(userId))
            .fetchOne();
        return Optional.ofNullable(sum);
    }

    @Override
    public List<CashTransactionDto> findAllByUser(UserEntity user) {
        return queryFactory
            .select(Projections.constructor(
                CashTransactionDto.class,
                cashEntity.id,
                cashEntity.type,
                cashEntity.amount,
                cashEntity.created_at,
                cashEntity.description
            ))
            .from(cashEntity)
            .where(cashEntity.user.eq(user))
            .fetch();
    }
}
