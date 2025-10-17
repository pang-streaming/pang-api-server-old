package com.pangapiserver.domain.category.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = 745082270L;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final com.pangapiserver.domain.common.entity.QBaseEntity _super = new com.pangapiserver.domain.common.entity.QBaseEntity(this);

    public final EnumPath<com.pangapiserver.domain.category.enumeration.Chip> chip = createEnum("chip", com.pangapiserver.domain.category.enumeration.Chip.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath postImage = createString("postImage");

    public final ListPath<com.pangapiserver.domain.stream.entity.StreamEntity, com.pangapiserver.domain.stream.entity.QStreamEntity> streams = this.<com.pangapiserver.domain.stream.entity.StreamEntity, com.pangapiserver.domain.stream.entity.QStreamEntity>createList("streams", com.pangapiserver.domain.stream.entity.StreamEntity.class, com.pangapiserver.domain.stream.entity.QStreamEntity.class, PathInits.DIRECT2);

    public QCategoryEntity(String variable) {
        super(CategoryEntity.class, forVariable(variable));
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryEntity(PathMetadata metadata) {
        super(CategoryEntity.class, metadata);
    }

}

