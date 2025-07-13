package com.pangapiserver.domain.cash.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCashEntity is a Querydsl query type for CashEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCashEntity extends EntityPathBase<CashEntity> {

    private static final long serialVersionUID = -793955704L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCashEntity cashEntity = new QCashEntity("cashEntity");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> created_at = createDateTime("created_at", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final EnumPath<com.pangapiserver.domain.cash.enumeration.CashType> type = createEnum("type", com.pangapiserver.domain.cash.enumeration.CashType.class);

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QCashEntity(String variable) {
        this(CashEntity.class, forVariable(variable), INITS);
    }

    public QCashEntity(Path<? extends CashEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCashEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCashEntity(PathMetadata metadata, PathInits inits) {
        this(CashEntity.class, metadata, inits);
    }

    public QCashEntity(Class<? extends CashEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

