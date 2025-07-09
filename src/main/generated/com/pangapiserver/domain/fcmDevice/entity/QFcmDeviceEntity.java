package com.pangapiserver.domain.fcmDevice.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFcmDeviceEntity is a Querydsl query type for FcmDeviceEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFcmDeviceEntity extends EntityPathBase<FcmDeviceEntity> {

    private static final long serialVersionUID = -1581543352L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFcmDeviceEntity fcmDeviceEntity = new QFcmDeviceEntity("fcmDeviceEntity");

    public final BooleanPath is_alarm = createBoolean("is_alarm");

    public final ComparablePath<java.util.UUID> token_id = createComparable("token_id", java.util.UUID.class);

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QFcmDeviceEntity(String variable) {
        this(FcmDeviceEntity.class, forVariable(variable), INITS);
    }

    public QFcmDeviceEntity(Path<? extends FcmDeviceEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFcmDeviceEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFcmDeviceEntity(PathMetadata metadata, PathInits inits) {
        this(FcmDeviceEntity.class, metadata, inits);
    }

    public QFcmDeviceEntity(Class<? extends FcmDeviceEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

