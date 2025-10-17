package com.pangapiserver.domain.watchHistory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWatchHistoryEntity is a Querydsl query type for WatchHistoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWatchHistoryEntity extends EntityPathBase<WatchHistoryEntity> {

    private static final long serialVersionUID = -1104723540L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWatchHistoryEntity watchHistoryEntity = new QWatchHistoryEntity("watchHistoryEntity");

    public final com.pangapiserver.domain.common.entity.QBaseEntity _super = new com.pangapiserver.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.pangapiserver.domain.stream.entity.QStreamEntity stream;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QWatchHistoryEntity(String variable) {
        this(WatchHistoryEntity.class, forVariable(variable), INITS);
    }

    public QWatchHistoryEntity(Path<? extends WatchHistoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWatchHistoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWatchHistoryEntity(PathMetadata metadata, PathInits inits) {
        this(WatchHistoryEntity.class, metadata, inits);
    }

    public QWatchHistoryEntity(Class<? extends WatchHistoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stream = inits.isInitialized("stream") ? new com.pangapiserver.domain.stream.entity.QStreamEntity(forProperty("stream"), inits.get("stream")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

