package com.pangapiserver.application.payment;

import com.pangapiserver.application.payment.data.RegisterCardRequest;
import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.card.service.CardService;
import com.pangapiserver.domain.user.entity.UserEntity;
import com.pangapiserver.infrastructure.common.dto.Response;
import com.pangapiserver.infrastructure.payment.dto.RegisterCardResponse;
import com.pangapiserver.infrastructure.payment.exception.PayappException;
import com.pangapiserver.infrastructure.payment.service.PayappService;
import com.pangapiserver.infrastructure.security.support.UserAuthenticationHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentUseCase {
    private final PayappService payappService;
    private final UserAuthenticationHolder holder;
    private final CardService cardService;

    @Transactional
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

        if (result.state().equals("1")) {
            CardEntity card = CardEntity.builder()
                .encryptionKey(result.encBill())
                .number(request.phone())
                .provider(result.cardname())
                .user(user)
                .name(result.cardname())
                .phone(request.phone())
                .build();
            cardService.save(card);
            return Response.ok("카드 추가 성공");
        }else{
            throw new PayappException(result.errorMessage());
        }
    }

    public List<CardEntity> getCards() {
        UserEntity user = holder.current();
        return cardService.getCardList(user);
    }
}
