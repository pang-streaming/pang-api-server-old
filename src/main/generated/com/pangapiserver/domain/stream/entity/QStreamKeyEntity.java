package com.pangapiserver.domain.stream.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStreamKeyEntity is a Querydsl query type for StreamKeyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStreamKeyEntity extends EntityPathBase<StreamKeyEntity> {

    private static final long serialVersionUID = 1231055555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStreamKeyEntity streamKeyEntity = new QStreamKeyEntity("streamKeyEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath key = createString("key");

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QStreamKeyEntity(String variable) {
        this(StreamKeyEntity.class, forVariable(variable), INITS);
    }

    public QStreamKeyEntity(Path<? extends StreamKeyEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStreamKeyEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStreamKeyEntity(PathMetadata metadata, PathInits inits) {
        this(StreamKeyEntity.class, metadata, inits);
    }

    public QStreamKeyEntity(Class<? extends StreamKeyEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

