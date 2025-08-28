package com.pangapiserver.domain.market.service;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.ProductLikeEntity;
import com.pangapiserver.domain.market.repository.ProductLikeRepository;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;

    public void saveProduct(UserEntity user, ProductAddRequest request) {
        productRepository.save(
            ProductEntity.builder()
                .image(request.image())
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .fileUrl(request.fileUrl())
                .seller(user)
                .build()
        );
    }

    public List<ProductEntity> getItems() {
        return productRepository.findAll();
    }

    public ProductEntity getById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveLike(UserEntity user, ProductEntity product) {
        productLikeRepository.save(
            ProductLikeEntity.builder()
                .user(user)
                .product(product)
                .createAt(LocalDateTime.now())
                .build()
        );
    }

    public int getLikes(UUID id) {
        return productLikeRepository.countByProduct_Id(id);
    }
}
