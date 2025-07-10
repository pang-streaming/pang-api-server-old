package com.pangapiserver.application.cash;

import com.pangapiserver.application.cash.data.CashChargeRequest;
import com.pangapiserver.application.cash.data.CashResponse;
import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.card.service.CardService;
import com.pangapiserver.domain.cash.service.CashService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.DataResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.payment.service.PayappService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CashUseCase {
    private final UserAuthenticationHolder holder;
    private final CashService service;
    private final PayappService payappService;
    private final CardService cardService;

    @Transactional
    public Response chargeCash(CashChargeRequest request) {
        UserEntity user = holder.current();
        CardEntity card = cardService.getCard(request.cardId());
        String description = "펑 "+ request.amount() +"개 충전";

        payappService.paymentCard(
            card.getEncryption_key(),
            description,
            String.valueOf(request.amount()+ request.amount()/10),
            card.getPhone()
        );
        service.charge(user, request.amount(), description);
        return Response.ok("충전을 완료하였습니다.");
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
