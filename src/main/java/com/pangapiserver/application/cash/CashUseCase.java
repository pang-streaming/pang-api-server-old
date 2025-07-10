package com.pangapiserver.application.cash;

import com.pangapiserver.application.cash.data.CashResponse;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CashUseCase {
    private final UserAuthenticationHolder holder;
    private final CashService service;

    public Response chargeCash() {
        return Response.ok("success");
    }

    public DataResponse<CashResponse> getCash() {
        UserEntity user = holder.current();

        return DataResponse.ok(
            "캐시 조회 성공",
            new CashResponse(
                service.getBalance(user),
                service.getTransactions(user)
            )
        );
    }
}
