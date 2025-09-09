package com.pangapiserver.domain.market.repository;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.QProductEntity;
import com.pangapiserver.domain.market.entity.QPurchaseEntity;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PurchaseCustomRepositoryImpl implements PurchaseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductEntity> findProductByBuyer(UserEntity buyer) {
        QPurchaseEntity qPurchaseEntity = QPurchaseEntity.purchaseEntity;

        return jpaQueryFactory
            .select(qPurchaseEntity.product)
            .from(qPurchaseEntity)
            .where(qPurchaseEntity.buyer.eq(buyer))
            .fetch();
    }
}
