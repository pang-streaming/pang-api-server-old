package com.pangapiserver.domain.market.service;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final ProductRepository productRepository;

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
}
