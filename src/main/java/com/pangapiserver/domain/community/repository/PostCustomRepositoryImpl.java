package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.entity.QCommunityEntity;
import com.pangapiserver.domain.community.entity.QPostEntity;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostEntity> findPostsByCommunity(UserEntity user, Long communityId, Pageable pageable, PostFilterType filter) {
        QPostEntity post = QPostEntity.postEntity;
        QCommunityEntity community = QCommunityEntity.communityEntity;

        JPQLQuery<PostEntity> query = queryFactory
                .selectFrom(post)
                .join(post.community, community).fetchJoin()
                .where(community.id.eq(communityId), getFilterExpression(user, filter, post, community));

        if (filter == PostFilterType.ALL) {
            NumberExpression<Integer> priorityOrder = new CaseBuilder()
                    .when(post.user.eq(community.user))
                    .then(0)
                    .otherwise(1);
            query.orderBy(priorityOrder.asc(), post.createdAt.desc());
        } else {
            query.orderBy(post.createdAt.desc());
        }

        long total = query.fetchCount();

        List<PostEntity> content = query
                .offset((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression getFilterExpression(UserEntity user, PostFilterType filter, QPostEntity post, QCommunityEntity community) {
        return switch (filter) {
            case OWNER_ONLY -> post.user.eq(community.user);
            case NON_OWNER_ONLY -> post.user.ne(community.user);
            default -> null;
        };
    }
}
