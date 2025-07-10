package com.pangapiserver.domain.subscribe.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSubscribeEntity is a Querydsl query type for SubscribeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSubscribeEntity extends EntityPathBase<SubscribeEntity> {

    private static final long serialVersionUID = -2137118520L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubscribeEntity subscribeEntity = new QSubscribeEntity("subscribeEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.pangapiserver.domain.user.entity.QUserEntity streamer;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QSubscribeEntity(String variable) {
        this(SubscribeEntity.class, forVariable(variable), INITS);
    }

    public QSubscribeEntity(Path<? extends SubscribeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSubscribeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSubscribeEntity(PathMetadata metadata, PathInits inits) {
        this(SubscribeEntity.class, metadata, inits);
    }

    public QSubscribeEntity(Class<? extends SubscribeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.streamer = inits.isInitialized("streamer") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("streamer")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

