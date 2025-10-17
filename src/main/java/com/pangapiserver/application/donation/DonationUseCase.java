package com.pangapiserver.application.donation;

import com.pangapiserver.application.donation.data.DonationRequest;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.domain.user.service.UserService;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class DonationUseCase {
    private final UserAuthenticationHolder holder;
    private final CashService cashService;
    private final UserService userService;

    public Response createDonation(DonationRequest request) {
        UserEntity current = holder.current();
        UserEntity streamer = userService.getByUsername(request.username());

        cashService.withdraw(current, request.amount(), streamer.getNickname() + "님에게 후원");
        cashService.deposit(streamer, request.amount(), "후원");
        return Response.ok("도네이션 생성을 완료하였습니다.");
    }
}
