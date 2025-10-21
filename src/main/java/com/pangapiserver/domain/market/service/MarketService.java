package com.pangapiserver.domain.market.service;

import com.pangapiserver.application.market.data.ProductAddRequest;
import com.pangapiserver.application.market.data.ProductWithLikeStatusDto;
import com.pangapiserver.domain.market.document.ProductDocument;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.entity.ProductLikeEntity;
import com.pangapiserver.domain.market.enumeration.LikeStatus;
import com.pangapiserver.domain.market.enumeration.ProductCategory;
import com.pangapiserver.domain.market.exception.ProductNotFoundException;
import com.pangapiserver.domain.market.repository.ProductDocumentRepository;
import com.pangapiserver.domain.market.repository.ProductLikeRepository;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.market.repository.PurchaseRepository;
import com.pangapiserver.domain.store.entity.StoreEntity;
import com.pangapiserver.domain.store.exception.StoreNotFoundException;
import com.pangapiserver.domain.store.exception.StoreNotJoinedException;
import com.pangapiserver.domain.store.repository.StoreRepository;
import com.pangapiserver.domain.store.repository.StoreUserRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final StoreUserRepository storeUserRepository;
    private final StoreRepository storeRepository;
    private final ProductDocumentRepository productDocumentRepository;

    public void saveProduct(ProductAddRequest request, UUID userId) {
        if (storeUserRepository.existsByUserIdAndStoreId(userId, request.storeId())) {
            StoreEntity store = storeRepository.findById(request.storeId())
                    .orElseThrow(StoreNotFoundException::new);
            ProductEntity entity = ProductEntity.builder()
                    .imageUrl(request.image())
                    .name(request.name())
                    .description(request.description())
                    .price(request.price())
                    .fileUrl(request.fileUrl())
                    .category(request.category())
                    .store(store)
                    .build();
            productRepository.save(entity);
            saveDocument(entity);
        } else {
            throw new StoreNotJoinedException();
        }
    }

    public Page<ProductWithLikeStatusDto> getItemsWithLikeStatus(UserEntity user, Pageable pageable) {
        return productRepository.findAllWithLikeStatus(user, pageable);
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
        System.out.println("MarketService#######################productId: " + id);
        return productLikeRepository.countByProduct_Id(id);
    }

    public List<ProductEntity> getTop5LikedProducts() {
        return productRepository.findTop5ByOrderByLikesDesc();
    }

    public boolean isProductLikedByUser(UserEntity user, ProductEntity product) {
        return productLikeRepository.findByUserAndProduct(user, product).isPresent();
    }

    public List<ProductEntity> getItemsByCategory(ProductCategory category) {
        return productRepository.findByCategory(category);
    }

    public void deleteById(UUID id) {
        productRepository.deleteById(id);
        deleteDocument(id);
    }

    private void saveDocument(ProductEntity product) {
        productDocumentRepository.save(
                ProductDocument.builder()
                        .id(product.getId())
                        .image(product.getImageUrl())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build()
        );
    }

    private void deleteDocument(UUID id) {
        productDocumentRepository.deleteById(id);
    }
}
