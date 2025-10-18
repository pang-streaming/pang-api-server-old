package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.QProductLikeEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProductLikeCustomRepositoryImpl implements ProductLikeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UUID> findLikedProductIdsByUserId(UUID userId) {
        QProductLikeEntity entity = QProductLikeEntity.productLikeEntity;

        return jpaQueryFactory
                .select(entity.product.id)
                .from(entity)
                .where(entity.user.id.eq(userId))
                .fetch();
    }

}
