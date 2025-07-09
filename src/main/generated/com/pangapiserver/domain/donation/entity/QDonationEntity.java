package com.pangapiserver.domain.donation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDonationEntity is a Querydsl query type for DonationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDonationEntity extends EntityPathBase<DonationEntity> {

    private static final long serialVersionUID = -1678869306L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDonationEntity donationEntity = new QDonationEntity("donationEntity");

    public final StringPath content = createString("content");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.pangapiserver.domain.user.entity.QUserEntity streamer;

    public final com.pangapiserver.domain.user.entity.QUserEntity user;

    public QDonationEntity(String variable) {
        this(DonationEntity.class, forVariable(variable), INITS);
    }

    public QDonationEntity(Path<? extends DonationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDonationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDonationEntity(PathMetadata metadata, PathInits inits) {
        this(DonationEntity.class, metadata, inits);
    }

    public QDonationEntity(Class<? extends DonationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.streamer = inits.isInitialized("streamer") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("streamer")) : null;
        this.user = inits.isInitialized("user") ? new com.pangapiserver.domain.user.entity.QUserEntity(forProperty("user")) : null;
    }

}

