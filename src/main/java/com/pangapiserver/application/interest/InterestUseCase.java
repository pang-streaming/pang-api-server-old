package com.pangapiserver.application.interest;

import com.pangapiserver.application.interest.data.AddInterestsRequest;
import com.pangapiserver.domain.interest.entity.InterestEntity;
import com.pangapiserver.domain.interest.repository.InterestRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
@RequiredArgsConstructor
public class InterestUseCase {
    private final InterestRepository interestRepository;
    private final UserAuthenticationHolder holder;

    public Response addInterests(AddInterestsRequest addInterestsRequest) {
        UserEntity user = holder.current();
        interestRepository.deleteAllByUser(user);

        List<InterestEntity> interestEntities = addInterestsRequest.interests().stream()
            .map(chip -> InterestEntity.builder().user(user).chip(chip).build())
            .toList();
        interestRepository.saveAll(interestEntities);
        return Response.ok("관심사 설정 완료");
    }
}
