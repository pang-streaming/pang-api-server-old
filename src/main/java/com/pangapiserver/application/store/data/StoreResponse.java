package com.pangapiserver.application.store.data;

import com.pangapiserver.domain.store.entity.StoreEntity;

import java.util.UUID;

public record StoreResponse(
    UUID id,
    String name,
    String description,
    String profileImage,
    String bannerImage
) {
    public static StoreResponse of(StoreEntity store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getDescription(),
                store.getProfileImage(),
                store.getBannerImage()
        );
    }
}
