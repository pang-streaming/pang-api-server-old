package com.pangapiserver.application.payment.data;

import com.pangapiserver.domain.card.entity.CardEntity;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CardDto {
    private UUID cardId;
    private String number;
    private String provider;
    private String name;

    public CardDto(CardEntity entity) {
        this.cardId = entity.getCardId();
        this.number = entity.getNumber();
        this.provider = entity.getProvider();
        this.name = entity.getName();
    }
}
