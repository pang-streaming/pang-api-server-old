package com.pangapiserver.domain.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseEntity is a Querydsl query type for PurchaseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseEntity extends EntityPathBase<PurchaseEntity> {

    private static final long serialVersionUID = 73495711L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseEntity purchaseEntity = new QPurchaseEntity("purchaseEntity");

    public final com.pangapiserver.domain.user.entity.QUserEntity buyer;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QProductEntity product;

    public QPurchaseEntity(String variable) {
        this(PurchaseEntity.class, forVariable(variable), INITS);
    }

    public QPurchaseEntity(Path<? extends PurchaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseEntity(PathMetadata metadata, PathInits inits) {
        this(PurchaseEntity.class, metadata, inits);
    }

    public QPurchaseEntity(Class<? extends PurchaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.buyer = inits.isInitialized("buyer") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("buyer")) : null;
        this.product = inits.isInitialized("product") ? new QProductEntity(forProperty("product"), inits.get("product")) : null;
    }

}

