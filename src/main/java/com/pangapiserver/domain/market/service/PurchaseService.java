package com.pangapiserver.domain.market.service;

import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.PurchaseEntity;
import com.pangapiserver.domain.market.enumeration.DeliveryStatus;
import com.pangapiserver.domain.market.repository.PurchaseRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository repository;

    public void save(UserEntity user, ProductEntity product, String address, String email) {
        PurchaseEntity entity = PurchaseEntity.builder()
            .product(product)
            .buyer(user)
            .address(address)
            .email(email)
            .deliveryStatus(DeliveryStatus.PREPARING)
            .createdAt(LocalDateTime.now())
            .build();
        repository.save(entity);
    }

    public List<ProductEntity> getByUser(UserEntity user) {
        return repository.findProductByBuyer(user);
    }

    public boolean existsByUserAndProduct(UserEntity user, ProductEntity product) {
        return repository.existsByBuyerAndProduct(user, product);
    }

    public List<PurchaseEntity> getPurchasesByUser(UserEntity user) {
        return repository.findByBuyer(user);
    }
}
