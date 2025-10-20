package com.pangapiserver.domain.community.repository;

import com.pangapiserver.domain.community.entity.PostEntity;
import com.pangapiserver.domain.community.entity.QCommunityEntity;
import com.pangapiserver.domain.community.entity.QPostEntity;
import com.pangapiserver.domain.community.enumeration.PostFilterType;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostEntity> findPostsByCommunity(UserEntity user, Long communityId, Pageable pageable, PostFilterType filter) {
        QPostEntity post = QPostEntity.postEntity;
        QCommunityEntity community = QCommunityEntity.communityEntity;

        long total = Optional.ofNullable(queryFactory
                .select(post.count())
                .from(post)
                .join(post.community, community)
                .where(post.community.id.eq(communityId), getFilterExpression(user, filter, post, community))
                .fetchOne()).orElse(0L);

        List<Long> ids = queryFactory
                .select(post.id)
                .from(post)
                .join(post.community, community)
                .where(post.community.id.eq(communityId), getFilterExpression(user, filter, post, community))
                .orderBy(getOrderSpecifiers(filter, post, community))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (ids.isEmpty()) {
            return new PageImpl<>(Collections.emptyList(), pageable, total);
        }

        List<PostEntity> content = queryFactory
                .selectFrom(post)
                .join(post.community, community).fetchJoin()
                .join(post.user).fetchJoin()
                .leftJoin(post.images).fetchJoin()
                .where(post.id.in(ids))
                .orderBy(getOrderSpecifiers(filter, post, community))
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

    private OrderSpecifier<?>[] getOrderSpecifiers(PostFilterType filter, QPostEntity post, QCommunityEntity community) {
        if (filter == PostFilterType.ALL) {
            NumberExpression<Integer> priorityOrder = new CaseBuilder()
                .when(post.user.eq(community.user))
                .then(0)
                .otherwise(1);
            return new OrderSpecifier[]{priorityOrder.asc(), post.createdAt.desc()};
        }
        return new OrderSpecifier[]{post.createdAt.desc()};
    }
}