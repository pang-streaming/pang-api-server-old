package com.pangapiserver.domain.market.repository;

import com.pangapiserver.application.market.data.ProductWithLikeStatusDto;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.QProductEntity;
import com.pangapiserver.domain.market.entity.QProductLikeEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductEntity> findTop5ByOrderByLikesDesc() {
        QProductEntity product = QProductEntity.productEntity;
        QProductLikeEntity productLike = QProductLikeEntity.productLikeEntity;

        return queryFactory
                .select(product)
                .from(product)
                .leftJoin(productLike).on(product.id.eq(productLike.product.id))
                .groupBy(product.id)
                .orderBy(productLike.id.count().coalesce(0L).desc())
                .limit(5)
                .fetch();
    }

    @Override
    public Page<ProductWithLikeStatusDto> findAllWithLikeStatus(UserEntity user, Pageable pageable) {
        QProductEntity product = QProductEntity.productEntity;
        QProductLikeEntity productLike = QProductLikeEntity.productLikeEntity;

        Long total = queryFactory
                .select(product.count())
                .from(product)
                .fetchOne();

        List<ProductWithLikeStatusDto> content = queryFactory
                .select(Projections.constructor(ProductWithLikeStatusDto.class,
                        product,
                        productLike.id.isNotNull()
                ))
                .from(product)
                .leftJoin(productLike)
                .on(product.id.eq(productLike.product.id)
                        .and(productLike.user.eq(user)))
                .offset((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }
}
