package com.pangapiserver.domain.category.repository;

import com.pangapiserver.application.category.data.CategoryData;
import com.pangapiserver.domain.category.entity.QCategoryEntity;
import com.pangapiserver.domain.stream.entity.QStreamEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<CategoryData> findAllWithStreamCount() {
        QCategoryEntity category = QCategoryEntity.categoryEntity;
        QStreamEntity stream = QStreamEntity.streamEntity;

        return queryFactory
            .select(Projections.constructor(CategoryData.class,
                category.id,
                category.name,
                category.chip,
                category.postImage,
                stream.id.count()
            ))
            .from(category)
            .leftJoin(category.streams, stream)
            .groupBy(category.id)
            .fetch();
    }
}
