package com.pangapiserver.domain.user.repository;

import com.pangapiserver.domain.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.pangapiserver.domain.user.entity.QUserEntity.userEntity;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserEntity> findUserWithInterestsByUsername(String username) {
        UserEntity result = queryFactory
                .selectFrom(userEntity)
                .leftJoin(userEntity.interestEntities).fetchJoin()
                .where(userEntity.username.eq(username))
                .fetchOne();
        return Optional.ofNullable(result);
    }
}
