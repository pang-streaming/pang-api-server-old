package com.pangapiserver.domain.card.service;

import com.pangapiserver.domain.card.entity.CardEntity;
import com.pangapiserver.domain.card.repository.CardRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;

    public CardEntity getCard(UUID cardId) {
        return repository.findByCardId(cardId);
    }

    public void save(CardEntity card) {
        repository.save(card);
    }

    public List<CardEntity> getCardList(UserEntity user) {
        return repository.findAllByUser(user);
    }
}
