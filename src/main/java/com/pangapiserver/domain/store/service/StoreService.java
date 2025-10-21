package com.pangapiserver.domain.store.service;

import com.pangapiserver.application.store.data.CreateStoreRequest;
import com.pangapiserver.application.store.data.StoreResponse;
import com.pangapiserver.domain.market.entity.ProductEntity;
import com.pangapiserver.domain.market.repository.ProductLikeRepository;
import com.pangapiserver.domain.market.repository.ProductRepository;
import com.pangapiserver.domain.store.entity.StoreEntity;
import com.pangapiserver.domain.store.entity.StoreUserEntity;
import com.pangapiserver.domain.store.exception.StoreAlreadyJoinedException;
import com.pangapiserver.domain.store.exception.StoreNotFoundException;
import com.pangapiserver.domain.store.repository.StoreRepository;
import com.pangapiserver.domain.store.repository.StoreUserRepository;
import com.pangapiserver.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository repository;
    private final StoreUserRepository storeUserRepository;
    private final ProductRepository productRepository;
    private final ProductLikeRepository productLikeRepository;

    public void createStore(CreateStoreRequest request, UserEntity user) {
        StoreEntity store = StoreEntity.builder()
                .name(request.name())
                .description(request.description())
                .profileImage(request.profileImage())
                .bannerImage(request.bannerImage())
                .build();
        repository.save(store);

        StoreUserEntity storeUserEntity = StoreUserEntity.builder()
                .user(user)
                .store(store)
                .build();
        storeUserRepository.save(storeUserEntity);
    }

    public List<StoreEntity> getAllStores() {
        return repository.findAll();
    }

    public List<StoreUserEntity> getJoinedStores(UserEntity user) {
        return storeUserRepository.findByUser(user);
    }

    public void saveStore(UUID storeId, UserEntity user) {
        StoreEntity store = repository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);
        if (storeUserRepository.existsByUserIdAndStoreId(user.getId(), storeId))
            throw new StoreAlreadyJoinedException();
        StoreUserEntity entity = StoreUserEntity.builder()
                .user(user)
                .store(store)
                .build();
        storeUserRepository.save(entity);
    }

    public List<ProductEntity> getProductsByStore(UUID storeId) {
        StoreEntity store = repository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);
        return productRepository.findByStore(store);
    }

    public List<UUID> findLikedProductIdsByUserId(UUID userId) {
        return productLikeRepository.findLikedProductIdsByUserId(userId);
    }

    public boolean deleteByStoreIdAndUser(UUID storeId, UserEntity user) {
        StoreEntity store = repository.findById(storeId)
                .orElseThrow(StoreNotFoundException::new);
        if (storeUserRepository.existsByUserIdAndStoreId(user.getId(), store.getId())) {
            storeUserRepository.deleteByStoreAndUser(store, user);
            return true;
        }
        return false;
    }
}
