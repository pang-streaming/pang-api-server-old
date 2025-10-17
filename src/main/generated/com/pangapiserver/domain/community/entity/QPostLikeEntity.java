package com.pangapiserver.domain.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostLikeEntity is a Querydsl query type for PostLikeEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostLikeEntity extends EntityPathBase<PostLikeEntity> {

    private static final long serialVersionUID = 994081310L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostLikeEntity postLikeEntity = new QPostLikeEntity("postLikeEntity");

    public final com.pangapiserver.domain.common.entity.QBaseEntity _super = new com.pangapiserver.domain.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QPostEntity post;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QPostLikeEntity(String variable) {
        this(PostLikeEntity.class, forVariable(variable), INITS);
    }

    public QPostLikeEntity(Path<? extends PostLikeEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostLikeEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostLikeEntity(PathMetadata metadata, PathInits inits) {
        this(PostLikeEntity.class, metadata, inits);
    }

    public QPostLikeEntity(Class<? extends PostLikeEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPostEntity(forProperty("post"), inits.get("post")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

