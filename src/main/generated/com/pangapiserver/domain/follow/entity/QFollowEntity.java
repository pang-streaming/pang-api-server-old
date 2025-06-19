package com.pangapiserver.domain.follow.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFollowEntity is a Querydsl query type for FollowEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFollowEntity extends EntityPathBase<FollowEntity> {

    private static final long serialVersionUID = -668951292L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFollowEntity followEntity = new QFollowEntity("followEntity");

    public final com.pangapiserver.domain.user.entity.QUserEntity follower;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isAlarm = createBoolean("isAlarm");

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QFollowEntity(String variable) {
        this(FollowEntity.class, forVariable(variable), INITS);
    }

    public QFollowEntity(Path<? extends FollowEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFollowEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFollowEntity(PathMetadata metadata, PathInits inits) {
        this(FollowEntity.class, metadata, inits);
    }

    public QFollowEntity(Class<? extends FollowEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.follower = inits.isInitialized("follower") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("follower")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

