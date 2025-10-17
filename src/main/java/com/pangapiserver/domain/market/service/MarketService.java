package com.pangapiserver.domain.market.service;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.application.market.data.ProductWithLikeStatusDto;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.ProductLikeEntity;
import com.pangapiserver.domain.market.enumeration.LikeStatus;
import com.pangapiserver.domain.market.exception.ProductNotFoundException;
import com.pangapiserver.domain.market.repository.ProductLikeRepository;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.market.repository.PurchaseRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;
    private final PurchaseRepository purchaseRepository;

    public void saveProduct(UserEntity user, ProductAddRequest request) {
        ProductEntity entity = ProductEntity.builder()
            .imageUrl(request.image())
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .fileUrl(request.fileUrl())
            .seller(user)
            .build();
        productRepository.save(entity);
    }

    public List<ProductWithLikeStatusDto> getItemsWithLikeStatus(UserEntity user) {
        return productRepository.findAllWithLikeStatus(user);
    }

    public ProductEntity getById(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(ProductNotFoundException::new);
    }

    public LikeStatus saveLike(UserEntity user, ProductEntity product) {
        Optional<ProductLikeEntity> entity = productLikeRepository.findByUserAndProduct(user, product);
        if (entity.isPresent()) {
            productLikeRepository.deleteById(entity.get().getId());
            return LikeStatus.UNLIKED;
        }
        productLikeRepository.save(
            ProductLikeEntity.builder()
                .user(user)
                .product(product)
                .createdAt(LocalDateTime.now())
                .build()
        );
        return LikeStatus.LIKED;
    }

    public int getLikes(UUID id) {
        return productLikeRepository.countByProduct_Id(id);
    }

    public List<ProductEntity> getTop5LikedProducts() {
        return productRepository.findTop5ByOrderByLikesDesc();
    }

    public boolean isProductLikedByUser(UserEntity user, ProductEntity product) {
        return productLikeRepository.findByUserAndProduct(user, product).isPresent();
    }
}
