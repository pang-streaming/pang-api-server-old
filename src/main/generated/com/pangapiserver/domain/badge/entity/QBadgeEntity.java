package com.pangapiserver.domain.badge.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBadgeEntity is a Querydsl query type for BadgeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBadgeEntity extends EntityPathBase<BadgeEntity> {

    private static final long serialVersionUID = -82866200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBadgeEntity badgeEntity = new QBadgeEntity("badgeEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath image = createString("image");

    public final com.pangapiserver.domain.user.entity.QUserEntity streamer;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QBadgeEntity(String variable) {
        this(BadgeEntity.class, forVariable(variable), INITS);
    }

    public QBadgeEntity(Path<? extends BadgeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBadgeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBadgeEntity(PathMetadata metadata, PathInits inits) {
        this(BadgeEntity.class, metadata, inits);
    }

    public QBadgeEntity(Class<? extends BadgeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.streamer = inits.isInitialized("streamer") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("streamer")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

