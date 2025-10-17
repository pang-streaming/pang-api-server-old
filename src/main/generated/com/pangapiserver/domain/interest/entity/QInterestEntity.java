package com.pangapiserver.domain.interest.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestEntity is a Querydsl query type for InterestEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestEntity extends EntityPathBase<InterestEntity> {

    private static final long serialVersionUID = 951609334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestEntity interestEntity = new QInterestEntity("interestEntity");

    public final com.pangapiserver.domain.common.entity.QBaseEntity _super = new com.pangapiserver.domain.common.entity.QBaseEntity(this);

    public final EnumPath<com.pangapiserver.domain.category.enumeration.Chip> chip = createEnum("chip", com.pangapiserver.domain.category.enumeration.Chip.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QInterestEntity(String variable) {
        this(InterestEntity.class, forVariable(variable), INITS);
    }

    public QInterestEntity(Path<? extends InterestEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestEntity(PathMetadata metadata, PathInits inits) {
        this(InterestEntity.class, metadata, inits);
    }

    public QInterestEntity(Class<? extends InterestEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

