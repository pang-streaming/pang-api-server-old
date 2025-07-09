package com.pangapiserver.domain.alarm.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAlarmEntity is a Querydsl query type for AlarmEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAlarmEntity extends EntityPathBase<AlarmEntity> {

    private static final long serialVersionUID = -1366013272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAlarmEntity alarmEntity = new QAlarmEntity("alarmEntity");

    public final ComparablePath<java.util.UUID> alarm_id = createComparable("alarm_id", java.util.UUID.class);

    public final StringPath content = createString("content");

    public final StringPath title = createString("title");

    public final StringPath uri = createString("uri");

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QAlarmEntity(String variable) {
        this(AlarmEntity.class, forVariable(variable), INITS);
    }

    public QAlarmEntity(Path<? extends AlarmEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAlarmEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAlarmEntity(PathMetadata metadata, PathInits inits) {
        this(AlarmEntity.class, metadata, inits);
    }

    public QAlarmEntity(Class<? extends AlarmEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

