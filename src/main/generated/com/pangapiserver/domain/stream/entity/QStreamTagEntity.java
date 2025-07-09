package com.pangapiserver.domain.stream.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStreamTagEntity is a Querydsl query type for StreamTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStreamTagEntity extends EntityPathBase<StreamTagEntity> {

    private static final long serialVersionUID = 672363454L;

    public static final QStreamTagEntity streamTagEntity = new QStreamTagEntity("streamTagEntity");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QStreamTagEntity(String variable) {
        super(StreamTagEntity.class, forVariable(variable));
    }

    public QStreamTagEntity(Path<? extends StreamTagEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStreamTagEntity(PathMetadata metadata) {
        super(StreamTagEntity.class, metadata);
    }

}

