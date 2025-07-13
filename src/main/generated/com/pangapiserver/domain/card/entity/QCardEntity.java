package com.pangapiserver.domain.card.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardEntity is a Querydsl query type for CardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardEntity extends EntityPathBase<CardEntity> {

    private static final long serialVersionUID = 1994898242L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardEntity cardEntity = new QCardEntity("cardEntity");

    public final ComparablePath<java.util.UUID> cardId = createComparable("cardId", java.util.UUID.class);

    public final StringPath encryptionKey = createString("encryptionKey");

    public final StringPath name = createString("name");

    public final StringPath number = createString("number");

    public final StringPath phone = createString("phone");

    public final StringPath provider = createString("provider");

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QCardEntity(String variable) {
        this(CardEntity.class, forVariable(variable), INITS);
    }

    public QCardEntity(Path<? extends CardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardEntity(PathMetadata metadata, PathInits inits) {
        this(CardEntity.class, metadata, inits);
    }

    public QCardEntity(Class<? extends CardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

