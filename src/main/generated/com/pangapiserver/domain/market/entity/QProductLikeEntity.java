package com.pangapiserver.domain.market.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductLikeEntity is a Querydsl query type for ProductLikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductLikeEntity extends EntityPathBase<ProductLikeEntity> {

    private static final long serialVersionUID = 689337966L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductLikeEntity productLikeEntity = new QProductLikeEntity("productLikeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final QProductEntity product;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QProductLikeEntity(String variable) {
        this(ProductLikeEntity.class, forVariable(variable), INITS);
    }

    public QProductLikeEntity(Path<? extends ProductLikeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductLikeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductLikeEntity(PathMetadata metadata, PathInits inits) {
        this(ProductLikeEntity.class, metadata, inits);
    }

    public QProductLikeEntity(Class<? extends ProductLikeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProductEntity(forProperty("product"), inits.get("product")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

