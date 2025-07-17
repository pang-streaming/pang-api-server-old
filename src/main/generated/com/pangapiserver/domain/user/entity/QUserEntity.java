package com.pangapiserver.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserEntity is a Querydsl query type for UserEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserEntity extends EntityPathBase<UserEntity> {

    private static final long serialVersionUID = -2092217416L;

    public static final QUserEntity userEntity = new QUserEntity("userEntity");

    public final DatePath<java.time.LocalDate> age = createDate("age", java.time.LocalDate.class);

    public final StringPath bannerImage = createString("bannerImage");

    public final StringPath description = createString("description");

    public final StringPath email = createString("email");

    public final EnumPath<com.pangapiserver.domain.user.enumeration.Gender> gender = createEnum("gender", com.pangapiserver.domain.user.enumeration.Gender.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final BooleanPath isAdult = createBoolean("isAdult");

    public final BooleanPath isAlarm = createBoolean("isAlarm");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final EnumPath<com.pangapiserver.domain.user.enumeration.Role> role = createEnum("role", com.pangapiserver.domain.user.enumeration.Role.class);

    public final StringPath username = createString("username");

    public QUserEntity(String variable) {
        super(UserEntity.class, forVariable(variable));
    }

    public QUserEntity(Path<? extends UserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserEntity(PathMetadata metadata) {
        super(UserEntity.class, metadata);
    }

}

