package com.pangapiserver.application.payment;

import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.card.service.CardService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.ErrorResponse;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.payment.dto.RegisterCardResponse;
import com.pangapiserver.infrastructure.payment.properties.PaymentProperties;
import com.pangapiserver.infrastructure.payment.service.PayappService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {
    private final PayappService payappService;
    private final UserAuthenticationHolder holder;
    private final CardService cardService;

    public Response registerCard(RegisterCardRequest request) {
        UserEntity user = holder.current();

        RegisterCardResponse result = payappService.registerCard(
            user.getEmail(),
            request.cardNumber(),
            request.expiredYear(),
            request.expiredMonth(),
            request.birth(),
            request.cardPassword(),
            request.phone(),
            request.name()
        );

        if (result.getState().equals("1")) {
            CardEntity card = CardEntity.builder()
                .encryption_key(result.getEncBill())
                .number(request.phone())
                .provider(result.getCardname())
                .user(user)
                .name(result.getCardno())
                .phone(request.phone())
                .build();
            cardService.save(card);
            return Response.ok("카드 추가 성공");
        }else{
            return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(result.getErrorMessage())
                .build();
        }
    }

    public List<CardEntity> getCards() {
        UserEntity user = holder.current();
        return cardService.getCardList(user);
    }
}
