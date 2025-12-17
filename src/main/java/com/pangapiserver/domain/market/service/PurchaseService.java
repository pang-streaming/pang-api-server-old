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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository repository;

    public void save(UserEntity user, ProductEntity product, String address, String email, String phone) {
        PurchaseEntity entity = PurchaseEntity.builder()
            .product(product)
            .buyer(user)
            .address(address)
            .email(email)
            .phone(phone)
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

    public List<PurchaseEntity> getReceivedGiftsByUser(UserEntity user) {
        return repository.findByReceiver(user);
    }

    public PurchaseEntity getById(UUID purchaseId) {
        return repository.findById(purchaseId)
            .orElseThrow(() -> new RuntimeException("Purchase not found"));
    }

    public void updatePurchase(PurchaseEntity purchase) {
        repository.save(purchase);
    }
}
