package com.pangapiserver.domain.stream.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStreamEntity is a Querydsl query type for StreamEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStreamEntity extends EntityPathBase<StreamEntity> {

    private static final long serialVersionUID = -980979742L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStreamEntity streamEntity = new QStreamEntity("streamEntity");

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QStreamEntity(String variable) {
        this(StreamEntity.class, forVariable(variable), INITS);
    }

    public QStreamEntity(Path<? extends StreamEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStreamEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStreamEntity(PathMetadata metadata, PathInits inits) {
        this(StreamEntity.class, metadata, inits);
    }

    public QStreamEntity(Class<? extends StreamEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

