package com.pangapiserver.domain.community.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommunityEntity is a Querydsl query type for CommunityEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommunityEntity extends EntityPathBase<CommunityEntity> {

    private static final long serialVersionUID = -739052888L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommunityEntity communityEntity = new QCommunityEntity("communityEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QCommunityEntity(String variable) {
        this(CommunityEntity.class, forVariable(variable), INITS);
    }

    public QCommunityEntity(Path<? extends CommunityEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommunityEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommunityEntity(PathMetadata metadata, PathInits inits) {
        this(CommunityEntity.class, metadata, inits);
    }

    public QCommunityEntity(Class<? extends CommunityEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

